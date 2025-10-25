<template>
  <div class="app-container">

    <!-- 这里将放置组件的UI元素,即网页长什么样子 -->
    <!-- -------------------------------搜索栏-------------------------------- -->
    <el-form :inline="true" :model="queryParams" ref="queryForm" size="small" label-width="68px">
      <el-form-item label="名称" prop="typeName">
        <el-input v-model="queryParams.typeName" placeholder="请输入类型名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="typeStatus">
        <el-select v-model="queryParams.typeStatus" placeholder="请选择类型状态" clearable>
          <!-- 访问字典数据 -->
          <el-option v-for="dict in dict.type.device_type_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <!-- -------------------------------搜索栏：搜索重置按钮-------------------------------- -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- -------------------------------按钮-------------------------------- -->

    <el-row :gutter="10" class="mb8">
      <!-- -------------------------------新增按钮-------------------------------- -->
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <!-- -------------------------------删除按钮-------------------------------- -->
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
          @click="handleDelete">删除</el-button>
      </el-col>
      <!-- -------------------------------修改按钮-------------------------------- -->
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single"
          @click="handleUpdate">修改</el-button>
      </el-col>
      <!-- -------------------------------导出按钮-------------------------------- -->
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport">导出</el-button>
      </el-col>
    </el-row>
    <!-- ---------------------------设备信息操作框-------------------------------- -->
    <el-table :data="deviceTypeList" :border="true" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center">
      </el-table-column>
      <el-table-column prop="typeName" label="名称" align="center">
      </el-table-column>
      <el-table-column prop="typeRemark" label="说明" align="center">
      </el-table-column>
      <el-table-column prop="typeStatus" label="状态" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
      </el-table-column>
      <!-- -------------------------------操作框删除列-------------------------------- -->
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- -------------------------------新增弹框-------------------------------- -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <!-- -------------------------------新增弹框2.0-------------------------------- -->
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入类型名称" />
        </el-form-item>
        <el-form-item label="说明" prop="typeRemark">
          <el-input v-model="form.typeRemark" placeholder="请输入类型说明" />
        </el-form-item>
        <el-form-item label="状态" prop="typeStatus">
          <el-radio-group v-model="form.typeStatus">
            <el-radio v-for="dict in dict.type.device_type_status" :key="dict.value" :label="parseInt(dict.value)">{{
              dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <!-- -------------------------------新增弹框3.0-------------------------------- -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import { listDeviceType, delDeviceType, addDeviceType, getDeviceType, updateDeviceType } from "@/api/device/deviceType";
import { Loading } from "element-ui";

export default {
  name: "DeviceType", // 组件名称
  dicts: ['device_type_status'],
  data() {
    return {
      // 在这里声明组件的响应式数据
      Loading: true,
      total: 0,
      deviceTypeList: [],
      // -------------------------------搜索栏--------------------------------
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: null,
        typeRemark: null,
        typeStatus: null,
      },
      // -------------------------------操作框多选--------------------------------
      // 多选框选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //------------------------------新增弹框--------------------------------
      //是否显示弹出层
      open: false,
      title: "",
      // -------------------------------新增弹框2.0--------------------------------
      // 新增数据表单
      form: {},
      // 表单校验规则
      rules: {
        typeName: [
          { required: true, message: "类型名称不能为空", trigger: "blur" }
        ],
      }

    };
  },
  created() {
    // 组件创建后执行的逻辑，如API调用等
    this.getList();
  },
  methods: {
    // 定义组件的方法
    getList() {
      listDeviceType(this.queryParams).then(response => {
        this.deviceTypeList = response.rows;
        this.total = response.total;
        this.Loading = false;
      })
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** ---------------------------------搜索栏：搜索重置按钮--------------------------------- */

    /** 重置按钮操作 */
    resetQuery() {
      this.$refs["queryForm"].resetFields();
      this.handleQuery();
    },
    /** -------------------------------操作框多选-------------------------------- */
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.typeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** -------------------------------删除按钮-------------------------------- */
    handleDelete() {
      const typeIds = this.ids;
      this.$modal.confirm('是否确认删除设备分类编号为"' + typeIds + '"的数据项？').then(function () {
        return delDeviceType(typeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** -------------------------------删除按钮/操作框删除列-------------------------------- */
    handleDelete(row) {
      const typeIds = row.typeId || this.ids;
      this.$modal.confirm('是否确认删除设备分类编号为"' + typeIds + '"的数据项？').then(function () {
        return delDeviceType(typeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** -------------------------------新增按钮-------------------------------- */
    handleAdd() {
      this.open = true;
      this.title = "添加设备分类";
      /** -------------------------------新增弹框3.0-------------------------------- */
      // 新增时需要重置表单内容
      this.reset();
    },
    /** -------------------------------新增弹框3.0-------------------------------- */
    // 取消按钮
    cancel() {
      this.open = false;
      // 取消时也需要重置表单内容
      this.reset();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          /** -------------------------------修改-------------------------------- */
          if (this.form.typeId != null) {
            updateDeviceType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
            /** -------------------------------新增-------------------------------- */
          } else {
            addDeviceType(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 表单重置
    reset() {
      this.form = {
        typeId: null,
        typeName: null,
        typeRemark: null,
        typeStatus: 0,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      //若依框架的 this.resetForm("form") 已经封装了表单重置逻辑(包括重置数据和验证状态)，
      // 此时不需要再调用this.$refs["form"].resetFields();
      this.resetForm("form");
    },
    /** -------------------------------操作框修改列/修改按钮-------------------------------- */
    handleUpdate(row) {
      this.reset();
      const typeId = row.typeId || this.ids
      getDeviceType(typeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备分类";
      });
    },
    /** -------------------------------导出按钮-------------------------------- */
    handleExport() {
      this.download('device/deviceType/export', {
        ...this.queryParams
      }, `deviceType_${new Date().getTime()}.xlsx`)
    },
  },
};
</script>