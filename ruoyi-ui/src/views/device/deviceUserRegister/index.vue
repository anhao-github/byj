<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="姓名" prop="regName">
          <el-input
            v-model="queryParams.regName"
            placeholder="请输入姓名"
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
            v-hasPermi="['system:register:add']"
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
            v-hasPermi="['system:register:edit']"
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
            v-hasPermi="['system:register:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:register:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="所属部门" align="center" prop="regDept" />
        <el-table-column label="工号/学号" align="center" prop="regCode" />
        <el-table-column label="姓名" align="center" prop="regName" />
        <el-table-column label="注册时间" align="center" prop="createTime" />
        <el-table-column label="审核状态" align="center" prop="regStatus" />
        <el-table-column label="审核人" align="center" prop="" />
        <el-table-column label="审核时间" align="center" prop="updateT" />
        <el-table-column label="审核说明" align="center" prop="regRemark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:register:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:register:remove']"
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
        <el-form-item label="归属部门" prop="regDeptId">
          <el-input v-model="form.regDeptId" placeholder="请输入归属部门" />
        </el-form-item>
        <el-form-item label="所属部门" prop="regDept">
          <el-input v-model="form.regDept" placeholder="请输入所属部门" />
        </el-form-item>
        <el-form-item label="工号/学号" prop="regCode">
          <el-input v-model="form.regCode" placeholder="请输入工号/学号" />
        </el-form-item>
        <el-form-item label="姓名" prop="regName">
          <el-input v-model="form.regName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="手机密码" prop="regMobile">
          <el-input v-model="form.regMobile" placeholder="请输入手机密码" />
        </el-form-item>
        <el-form-item label="登录密码" prop="regPassword">
          <el-input v-model="form.regPassword" placeholder="请输入登录密码" />
        </el-form-item>
        <el-form-item label="小程序openid" prop="regOpenid">
          <el-input v-model="form.regOpenid" placeholder="请输入小程序openid" />
        </el-form-item>
        <el-form-item label="审核说明" prop="regRemark">
          <el-input v-model="form.regRemark" placeholder="请输入审核说明" />
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
  import { listRegister, getRegister, delRegister, addRegister, updateRegister } from "@/api/device/register";
  
  export default {
    name: "Register",
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
        registerList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          regDeptId: null,
          regDept: null,
          regCode: null,
          regName: null,
          regMobile: null,
          regPassword: null,
          regOpenid: null,
          regStatus: null,
          regRemark: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          regDept: [
            { required: true, message: "所属部门不能为空", trigger: "blur" }
          ],
          regCode: [
            { required: true, message: "工号/学号不能为空", trigger: "blur" }
          ],
          regName: [
            { required: true, message: "姓名不能为空", trigger: "blur" }
          ],
          regMobile: [
            { required: true, message: "手机密码不能为空", trigger: "blur" }
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
        listRegister(this.queryParams).then(response => {
          this.registerList = response.rows;
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
          regId: null,
          regDeptId: null,
          regDept: null,
          regCode: null,
          regName: null,
          regMobile: null,
          regPassword: null,
          regOpenid: null,
          regStatus: null,
          regRemark: null,
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
        this.ids = selection.map(item => item.regId)
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
        const regId = row.regId || this.ids
        getRegister(regId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改【请填写功能名称】";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.regId != null) {
              updateRegister(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addRegister(this.form).then(response => {
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
        const regIds = row.regId || this.ids;
        this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + regIds + '"的数据项？').then(function() {
          return delRegister(regIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('device/register/export', {
          ...this.queryParams
        }, `register_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  