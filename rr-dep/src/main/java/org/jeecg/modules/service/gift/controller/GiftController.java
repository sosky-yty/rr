package org.jeecg.modules.service.gift.controller;

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
import org.jeecg.modules.service.gift.entity.Express;
import org.jeecg.modules.service.gift.entity.Gift;
import org.jeecg.modules.service.gift.vo.GiftPage;
import org.jeecg.modules.service.gift.service.IGiftService;
import org.jeecg.modules.service.gift.service.IExpressService;
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
 * @Description: 礼品兑换
 * @author： jeecg-boot
 * @date：   2019-04-22
 * @version： V1.0
 */
@RestController
@RequestMapping("/gift/gift")
@Slf4j
public class GiftController {
	@Autowired
	private IGiftService giftService;
	@Autowired
	private IExpressService expressService;
	
	/**
	  * 分页列表查询
	 * @param gift
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Gift>> queryPageList(Gift gift,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Gift>> result = new Result<IPage<Gift>>();
		QueryWrapper<Gift> queryWrapper = QueryGenerator.initQueryWrapper(gift, req.getParameterMap());
		Page<Gift> page = new Page<Gift>(pageNo, pageSize);
		IPage<Gift> pageList = giftService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param giftPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Gift> add(@RequestBody GiftPage giftPage) {
		Result<Gift> result = new Result<Gift>();
		try {
			Gift gift = new Gift();
			BeanUtils.copyProperties(giftPage, gift);
			
			giftService.saveMain(gift, giftPage.getExpressList());
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
	 * @param giftPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Gift> edit(@RequestBody GiftPage giftPage) {
		Result<Gift> result = new Result<Gift>();
		Gift gift = new Gift();
		BeanUtils.copyProperties(giftPage, gift);
		Gift giftEntity = giftService.getById(gift.getId());
		if(giftEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = giftService.updateById(gift);
			giftService.updateMain(gift, giftPage.getExpressList());
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
	public Result<Gift> delete(@RequestParam(name="id",required=true) String id) {
		Result<Gift> result = new Result<Gift>();
		Gift gift = giftService.getById(id);
		if(gift==null) {
			result.error500("未找到对应实体");
		}else {
			giftService.delMain(id);
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
	public Result<Gift> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Gift> result = new Result<Gift>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.giftService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<Gift> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Gift> result = new Result<Gift>();
		Gift gift = giftService.getById(id);
		if(gift==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(gift);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryExpressByMainId")
	public Result<List<Express>> queryExpressListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Express>> result = new Result<List<Express>>();
		List<Express> expressList = expressService.selectByMainId(id);
		result.setResult(expressList);
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
      QueryWrapper<Gift> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Gift gift = JSON.parseObject(deString, Gift.class);
              queryWrapper = QueryGenerator.initQueryWrapper(gift, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<GiftPage> pageList = new ArrayList<GiftPage>();
      List<Gift> giftList = giftService.list(queryWrapper);
      for (Gift gift : giftList) {
          GiftPage vo = new GiftPage();
          BeanUtils.copyProperties(gift, vo);
          List<Express> expressList = expressService.selectByMainId(gift.getId());
          vo.setExpressList(expressList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "礼品兑换列表");
      mv.addObject(NormalExcelConstants.CLASS, GiftPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("礼品兑换列表数据", "导出人:Jeecg", "导出信息"));
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
              List<GiftPage> list = ExcelImportUtil.importExcel(file.getInputStream(), GiftPage.class, params);
              for (GiftPage page : list) {
                  Gift po = new Gift();
                  BeanUtils.copyProperties(page, po);
                  giftService.saveMain(po, page.getExpressList());
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
