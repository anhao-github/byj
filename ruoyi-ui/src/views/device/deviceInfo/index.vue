<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="queryParams" ref="queryForm" size="small" label-width="68px">
      <el-form-item label="名称" prop="deviceName">
        <el-input v-model="queryParams.deviceName" placeholder="请输入类型名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="型号" prop="deviceModel">
        <el-input v-model="queryParams.deviceModel" placeholder="请输入设备型号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="deviceStatus">
        <el-select v-model="queryParams.deviceStatus" placeholder="请选择类型状态" clearable>
          <el-option v-for="dict in dict.type.device_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport">导出</el-button>
      </el-col>
    </el-row>
    <!-- 设备信息操作框 -->
    <el-table :data="deviceTypeList" :border="true" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="typeId" label="类别" align="center" prop="typeId" />
      <el-table-column type="deviceImg" label="照片" align="center" prop="deviceImg">
        <template #default="scope">
          <el-image style="width: 45px; height: 45px" :src="scope.row.deviceImg" :preview-src-list="[scope.row.deviceImg]" />
        </template>
      </el-table-column>
      <el-table-column type="deviceName" label="名称" align="center" prop="deviceName" />
      <el-table-column type="deviceModel" label="型号" align="center" prop="deviceModel" />
      <el-table-column type="deviceRemark" label="说明" align="center" prop="deviceRemark" />
      <el-table-column prop="deviceStatus" label="状态" align="center">
        <template #default="scope">
          <dict-tag :options="dict.type.device_status" :value="scope.row.deviceStatus" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增弹框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别" prop="typeId">
          <el-input v-model="form.typeId" placeholder="请输入设备分类Id" />
        </el-form-item>
        <el-form-item label="名称" prop="deviceName">
          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="型号" prop="deviceModel">
          <el-input v-model="form.deviceModel" placeholder="请输入设备型号" />
        </el-form-item>
        <el-form-item label="照片" prop="deviceImg">
          <image-upload v-model="form.deviceImg" :limit="1" />
        </el-form-item>
        <el-form-item label="说明" prop="deviceRemark">
          <el-input type="textarea" :rows="4" v-model="form.deviceRemark" placeholder="请输入设备说明" />
        </el-form-item>
        <el-form-item label="状态" prop="deviceStatus">
          <el-radio-group v-model="form.deviceStatus">
            <el-radio v-for="dict in dict.type.device_status" :key="dict.value" :label="parseInt(dict.value)">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDeviceInfo, delDeviceInfo, addDeviceInfo, getDeviceInfo, updateDeviceInfo } from "@/api/device/deviceInfo";
import ImageUpload from "@/components/ImageUpload";

export default {
  name: "DeviceInfo",
  components: {
    ImageUpload
  },
  dicts: ['device_status'],
  data() {
    return {
      deviceTypeList: [],
      loading: true,
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: null,
        typeRemark: null,
        typeStatus: null,
      },
      ids: [],
      single: true,
      multiple: true,
      open: false,
      title: "",
      form: {},
      rules: {
        typeId: [{ required: true, message: '设备类型不能为空', trigger: 'blur' }],
        deviceName: [
          { required: true, message: "类型名称不能为空", trigger: "blur" }
        ],
      },
      url: "http://localhost:8080",
      srcList: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      listDeviceInfo(this.queryParams).then(response => {
        this.deviceTypeList = response.rows;
        this.total = response.total;
        this.loading = false;
        for (let i = 0; i < this.deviceTypeList.length; i++) {
          this.deviceTypeList[i].deviceImg = this.url + this.deviceTypeList[i].deviceImg;
        }
      });
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.$refs["queryForm"].resetFields();
      this.handleQuery();
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceId);
      this.single = selection.length!== 1;
      this.multiple =!selection.length;
    },
    handleDelete(row) {
      const deviceIds = row.deviceId || this.ids;
      this.$modal
      .confirm('是否确认删除设备类型编号为"' + deviceIds + '"的数据项？')
      .then(() => {
          return delDeviceInfo(deviceIds);
        })
      .then(() => {
          this.getList();
          this.$modal.msgSuccess('删除成功');
        })
      .catch(() => { });
    },
    handleAdd() {
      this.open = true;
      this.title = "添加设备分类";
      this.reset();
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceId!= null) {
            updateDeviceInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    reset() {
      this.form = {
        typeId: null,
        typeName: null,
        deviceModel: null,
        deviceStatus: 0,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    handleUpdate(row) {
      const deviceIds = row.deviceId || this.ids;
      getDeviceInfo(deviceIds).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备分类";
      });
    },
    handleExport() {
      this.download('device/deviceInfo/export', {
        ...this.queryParams
      }, `deviceInfo_${new Date().getTime()}.xlsx`)
    },
  }
}
</script>