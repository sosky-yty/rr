<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户姓名">
              <a-input placeholder="请输入用户姓名" v-decorator="['name', validatorRules.name ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户密码">
              <a-input placeholder="请输入用户密码" v-decorator="['password', validatorRules.password ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户个人头像">
              <a-input placeholder="请输入用户个人头像" v-decorator="['imageUrl', validatorRules.imageUrl ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="性别，0/男,1/女">
              <a-input placeholder="请输入性别，0/男,1/女" v-decorator="['gender', validatorRules.gender ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户电话号码">
              <a-input placeholder="请输入用户电话号码" v-decorator="['phone', validatorRules.phone ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户积分值，由用户在平台上交易产生，可用于兑换礼品">
              <a-input-number placeholder="请输入用户积分值，由用户在平台上交易产生，可用于兑换礼品" style="width:100%" v-decorator="[ 'point', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="用户平台经验值，用于划分等级">
              <a-input-number placeholder="请输入用户平台经验值，用于划分等级" style="width:100%" v-decorator="[ 'experience', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="订单信息" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="orderItemTable.loading"
            :columns="orderItemTable.columns"
            :dataSource="orderItemTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        <a-tab-pane tab="用户地址" :key="refKeys[1]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[1]"
            :loading="addrDetailTable.loading"
            :columns="addrDetailTable.columns"
            :dataSource="addrDetailTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableOneToManyMixin } from '@/mixins/JEditableTableOneToManyMixin'

  export default {
    name: 'CustomerModal',
    mixins: [JEditableTableOneToManyMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          name: { rules: [{ required: true, message: '请输入用户姓名!' }] },
          password: { rules: [{ required: true, message: '请输入用户密码!' }] },
          imageUrl: { rules: [{ required: true, message: '请输入用户个人头像!' }] },
          gender: { rules: [{ required: true, message: '请输入性别，0/男,1/女!' }] },
          phone: { rules: [{ required: true, message: '请输入用户电话号码!' }] },
        },
        refKeys: ['orderItem', 'addrDetail', ],
        activeKey: 'orderItem',
        // 订单信息
        orderItemTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '废品名称',
              key: 'name',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '该废品详情的重量',
              key: 'weight',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '交易时废品单价',
              key: 'price',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '完成时间',
              key: 'finishTime',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '用户方便的上门回收开始时间',
              key: 'collectFromTime',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '用户方便的上门回收开始时间',
              key: 'collectEndTime',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '订单状态，0代表回收员未接单，0/回收员已接单未回收,1/回收员已回收未交接,2/回收员已交接，订单完成',
              key: 'state',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '外键,创建订单的用户id',
              key: 'customerId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '外键,接单的回收员id',
              key: 'collectorId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '外键,回收员接单时所属企业的id',
              key: 'companyId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '订单交易地点',
              key: 'addrDetail',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '用户对本订单评分',
              key: 'customerGrade',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '回收员对本订单评分',
              key: 'collectorGrade',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        // 用户地址
        addrDetailTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '地址名称',
              key: 'name',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '详细地址',
              key: 'addr',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '地址类型  0/小区,1/家庭住址',
              key: 'kind',
              type: FormTypes.inputNumber,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
          ]
        },
        url: {
          add: "/customer/customer/add",
          edit: "/customer/customer/edit",
          orderItem: {
            list: '/customer/customer/queryOrderItemByMainId'
          },
          addrDetail: {
            list: '/customer/customer/queryAddrDetailByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'name', 'password', 'imageUrl', 'gender', 'phone', 'point', 'experience', ))
          // 时间格式化
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.orderItem.list, params, this.orderItemTable)
          this.requestSubTableData(this.url.addrDetail.list, params, this.addrDetailTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        return {
          ...main, // 展开
          orderItemList: allValues.tablesValue[0].values,
          addrDetailList: allValues.tablesValue[1].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>