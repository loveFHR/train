<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center">
        <rocket-two-tone/>&nbsp;仿12306售票系统
      </h1>
      <a-form
        :model="loginForm"
        name="basic"
        autocomplete="off"
      >
        <a-form-item
          label=""
          name="mobile"
          :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="手机号"/>
        </a-form-item>

        <a-form-item
          label=""
          name="code"
          :rules="[{ required: true, message: '请输入验证码!' }]"
        >
          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
          <!--<a-input v-model:value="loginForm.code" placeholder="验证码"/>-->
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>

      </a-form>
    </a-col>
  </a-row>
</template>

<script>
import {defineComponent, ref} from 'vue';
import {notification} from 'ant-design-vue';
import {useRouter} from 'vue-router'
import {useMemberStore} from "@/stores/member.js";
import {memberSendCodeService, memberLoginService} from "@/api/member.js";

export default defineComponent({
  setup() {
    const router = useRouter();
    const memberStore = useMemberStore()

    const loginForm = ref({
      mobile: '13512341234',
      code: '',
    });

    const sendCode = async () => {
      const res = await memberSendCodeService({
        mobile: loginForm.value.mobile
      })
      if (res.code === 200) {
        notification.success({description: '发送验证码成功！'});
        loginForm.value.code = "123456";
      } else {
        notification.error({description: res.msg});
      }
    };

    const login = async () => {
      const res = await memberLoginService(loginForm.value)
      if (res.code === 200) {
        notification.success({description: '登录成功！'});
        memberStore.setMember(res.data)
        // 登录成功，跳到控台主页
        await router.push("/welcome");
      } else {
        notification.error({description: res.msg});
      }
    };

    return {
      loginForm,
      sendCode,
      login
    };
  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}

.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>
