<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="设备id" prop="deviceId">
          <el-input
            v-model="queryParams.deviceId"
            placeholder="请输入设备id"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="借用用户id" prop="userId">
          <el-input
            v-model="queryParams.userId"
            placeholder="请输入借用用户id"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="借用时间" prop="borrowTime">
          <el-date-picker clearable
            v-model="queryParams.borrowTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择借用时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="借用说明" prop="borrowRemark">
          <el-input
            v-model="queryParams.borrowRemark"
            placeholder="请输入借用说明"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="计划归还时间" prop="returnTime">
          <el-date-picker clearable
            v-model="queryParams.returnTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划归还时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归还说明" prop="returnRemark">
          <el-input
            v-model="queryParams.returnRemark"
            placeholder="请输入归还说明"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:borrow:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:borrow:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:borrow:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:borrow:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="borrowList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键" align="center" prop="borrowId" />
        <el-table-column label="设备id" align="center" prop="deviceId" />
        <el-table-column label="借用用户id" align="center" prop="userId" />
        <el-table-column label="借用时间" align="center" prop="borrowTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.borrowTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="借用说明" align="center" prop="borrowRemark" />
        <el-table-column label="计划归还时间" align="center" prop="returnTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.returnTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="归还状态，0申请借用，1未归还，2申请归还，3已归还" align="center" prop="returnStatus" />
        <el-table-column label="归还说明" align="center" prop="returnRemark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:borrow:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:borrow:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改【请填写功能名称】对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="设备id" prop="deviceId">
            <el-input v-model="form.deviceId" placeholder="请输入设备id" />
          </el-form-item>
          <el-form-item label="借用用户id" prop="userId">
            <el-input v-model="form.userId" placeholder="请输入借用用户id" />
          </el-form-item>
          <el-form-item label="借用时间" prop="borrowTime">
            <el-date-picker clearable
              v-model="form.borrowTime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择借用时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="借用说明" prop="borrowRemark">
            <el-input v-model="form.borrowRemark" placeholder="请输入借用说明" />
          </el-form-item>
          <el-form-item label="计划归还时间" prop="returnTime">
            <el-date-picker clearable
              v-model="form.returnTime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择计划归还时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="归还说明" prop="returnRemark">
            <el-input v-model="form.returnRemark" placeholder="请输入归还说明" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listBorrow, getBorrow, delBorrow, addBorrow, updateBorrow } from "@/api/device/borrow";
  
  export default {
    name: "Borrow",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 【请填写功能名称】表格数据
        borrowList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          deviceId: null,
          userId: null,
          borrowTime: null,
          borrowRemark: null,
          returnTime: null,
          returnStatus: null,
          returnRemark: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          deviceId: [
            { required: true, message: "设备id不能为空", trigger: "blur" }
          ],
          userId: [
            { required: true, message: "借用用户id不能为空", trigger: "blur" }
          ],
          borrowTime: [
            { required: true, message: "借用时间不能为空", trigger: "blur" }
          ],
          returnStatus: [
            { required: true, message: "归还状态，0申请借用，1未归还，2申请归还，3已归还不能为空", trigger: "change" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询【请填写功能名称】列表 */
      getList() {
        this.loading = true;
        listBorrow(this.queryParams).then(response => {
          this.borrowList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          borrowId: null,
          deviceId: null,
          userId: null,
          borrowTime: null,
          borrowRemark: null,
          returnTime: null,
          returnStatus: null,
          returnRemark: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.borrowId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加【请填写功能名称】";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const borrowId = row.borrowId || this.ids
        getBorrow(borrowId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改【请填写功能名称】";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.borrowId != null) {
              updateBorrow(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addBorrow(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const borrowIds = row.borrowId || this.ids;
        this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + borrowIds + '"的数据项？').then(function() {
          return delBorrow(borrowIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/borrow/export', {
          ...this.queryParams
        }, `borrow_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  