package org.jeecg.modules.service.company.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.service.customer.entity.OrderItem;
import org.jeecg.modules.service.company.entity.Company;
import org.jeecg.modules.service.company.vo.CompanyPage;
import org.jeecg.modules.service.company.service.ICompanyService;
import org.jeecg.modules.service.customer.service.IOrderItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Title: Controller
 * @Description: 资源回收站
 */
@RestController
@RequestMapping("/company/company")
@Slf4j
public class CompanyController {
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IOrderItemService orderItemService;
	
	/**
	  * 分页列表查询
	 * @param company
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Company>> queryPageList(Company company,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Company>> result = new Result<IPage<Company>>();
		QueryWrapper<Company> queryWrapper = QueryGenerator.initQueryWrapper(company, req.getParameterMap());
		Page<Company> page = new Page<Company>(pageNo, pageSize);
		IPage<Company> pageList = companyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param companyPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Company> add(@RequestBody CompanyPage companyPage) {
		Result<Company> result = new Result<Company>();
		try {
			Company company = new Company();
			BeanUtils.copyProperties(companyPage, company);
			
			companyService.saveMain(company, companyPage.getOrderItemList());
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param companyPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Company> edit(@RequestBody CompanyPage companyPage) {
		Result<Company> result = new Result<Company>();
		Company company = new Company();
		BeanUtils.copyProperties(companyPage, company);
		Company companyEntity = companyService.getById(company.getId());
		if(companyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = companyService.updateById(company);
			companyService.updateMain(company, companyPage.getOrderItemList());
			result.success("修改成功!");
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<Company> delete(@RequestParam(name="id",required=true) String id) {
		Result<Company> result = new Result<Company>();
		Company company = companyService.getById(id);
		if(company==null) {
			result.error500("未找到对应实体");
		}else {
			companyService.delMain(id);
			result.success("删除成功!");
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<Company> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Company> result = new Result<Company>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.companyService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<Company> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Company> result = new Result<Company>();
		Company company = companyService.getById(id);
		if(company==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(company);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryOrderItemByMainId")
	public Result<List<OrderItem>> queryOrderItemListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<OrderItem>> result = new Result<List<OrderItem>>();
		List<OrderItem> orderItemList = orderItemService.selectByMainId(id);
		result.setResult(orderItemList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Company> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Company company = JSON.parseObject(deString, Company.class);
              queryWrapper = QueryGenerator.initQueryWrapper(company, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CompanyPage> pageList = new ArrayList<CompanyPage>();
      List<Company> companyList = companyService.list(queryWrapper);
      for (Company company : companyList) {
          CompanyPage vo = new CompanyPage();
          BeanUtils.copyProperties(company, vo);
          List<OrderItem> orderItemList = orderItemService.selectByMainId(company.getId());
          vo.setOrderItemList(orderItemList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "资源回收站列表");
      mv.addObject(NormalExcelConstants.CLASS, CompanyPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资源回收站列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<CompanyPage> list = ExcelImportUtil.importExcel(file.getInputStream(), CompanyPage.class, params);
              for (CompanyPage page : list) {
                  Company po = new Company();
                  BeanUtils.copyProperties(page, po);
                  companyService.saveMain(po, page.getOrderItemList());
              }
              return Result.ok("文件导入成功！数据行数：" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage());
              return Result.error("文件导入失败！");
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
