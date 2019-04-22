<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="回收员姓名">
              <a-input placeholder="请输入回收员姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="回收员所属企业,0/资源回收平台1,1/资源回收平台2">
              <a-input placeholder="请输入回收员所属企业,0/资源回收平台1,1/资源回收平台2" v-model="queryParam.company"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <collector-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CollectorModal from './modules/CollectorModal'

  export default {
    name: "CollectorList",
    mixins: [JeecgListMixin],
    components: {
      CollectorModal
    },
    data () {
      return {
        description: '用户管理管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '回收员姓名',
            align:"center",
            dataIndex: 'name'
          },
          {
            title: '回收员所属企业,0/资源回收平台1,1/资源回收平台2',
            align:"center",
            dataIndex: 'company'
          },
          {
            title: '回收员密码',
            align:"center",
            dataIndex: 'password'
          },
          {
            title: '用户个人头像',
            align:"center",
            dataIndex: 'imageUrl'
          },
          {
            title: '回收员身份证号码',
            align:"center",
            dataIndex: 'idcardno'
          },
          {
            title: '性别，0/男,1/女',
            align:"center",
            dataIndex: 'gender'
          },
          {
            title: '回收员电话号码',
            align:"center",
            dataIndex: 'phone'
          },
          {
            title: '用户积分值，由用户在平台上交易产生，可用于兑换礼品',
            align:"center",
            dataIndex: 'point'
          },
          {
            title: '用户平台经验值，用于划分等级',
            align:"center",
            dataIndex: 'experience'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        // 请求参数
    	url: {
              list: "/collector/collector/list",
              delete: "/collector/collector/delete",
              deleteBatch: "/collector/collector/deleteBatch",
              exportXlsUrl: "collector/collector/exportXls",
              importExcelUrl: "collector/collector/importExcel",
           },
        }
      },
      computed: {
        importExcelUrl: function(){
          return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
        }
      },


    methods: {

      initDictConfig() {
      }

    }
  }
</script>
<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }
  .ant-card-body .table-operator {
    margin-bottom: 18px;
  }

  .ant-table-tbody .ant-table-row td {
    padding-top: 15px;
    padding-bottom: 15px;
  }

  .anty-row-operator button {
    margin: 0 5px
  }

  .ant-btn-danger {
    background-color: #ffffff
  }

  .ant-modal-cust-warp {
    height: 100%
  }

  .ant-modal-cust-warp .ant-modal-body {
    height: calc(100% - 110px) !important;
    overflow-y: auto
  }

  .ant-modal-cust-warp .ant-modal-content {
    height: 90% !important;
    overflow-y: hidden
  }
</style>