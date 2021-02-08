<template>
  <div>
      <v-container>
          <v-row>
              <v-col class="d-flex justify-center">
                <v-card>
                    <v-card-title>비밀번호 변경</v-card-title>
                    <v-card-subtitle> 안전한 비밀번호로 내정보를 보호하세요. </v-card-subtitle>
                    <v-card-text>
                        <ValidationProvider
                            name="password"
                            rules="required|password"
                            v-slot="{ errors }"
                        >
                        <v-text-field
                            v-model="old_password"
                            label="현재 비밀번호"
                            placeholder="Placeholder"
                            outlined
                            type="password"
                            required
                            :error-messages="errors"
                        ></v-text-field>
                        </ValidationProvider>
                        <ValidationProvider
                        name="new_password"
                        rules="required|password"
                        v-slot="{ errors }"
                        >
                        <v-text-field
                            v-model="new_password"
                            label="새 비밀번호"
                            placeholder="Placeholder"
                            outlined
                            type="password"
                            :error-messages="errors"
                        ></v-text-field>
                        </ValidationProvider>
                        <ValidationProvider
                        name="Password Confirm"
                        rules="required|password|passwordConfirm:@new_password"
                        v-slot="{ errors }"
                        >
                    <v-text-field
                        v-model="new_password_confirm"
                        label="새 비밀번호 확인"
                        placeholder="Placeholder"
                        outlined
                        type="password"
                        :error-messages="errors"
                    ></v-text-field>
                    </ValidationProvider>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn width=100% dark>확인</v-btn>
                    </v-card-actions>
                    <v-card-actions>
                        <v-btn width=100%>취소</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
          </v-row> 
      </v-container>
  </div>
</template>

<script>
import { ValidationProvider } from 'vee-validate';
import { extend } from 'vee-validate';
import * as rules from 'vee-validate/dist/rules';

Object.keys(rules).forEach((rule) => {
  extend(rule, rules[rule]);
});
extend('password', {
  message:
    'password should include lower-case, numeric digit, special chracter($@$!%*#?&).',
  validate: (value) => {
    return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
  },
});
extend('passwordConfirm', {
  params: ['target'],
  validate(value, { target }) {
    return value === target;
  },
  message: 'Password confirmation does not match',
});

export default {
    components: {
    ValidationProvider,
    },
    data() {
        return {
            old_password: '',
            new_password: '',
            new_password_confirm: '',
            member: {},
        }
    },
    created() {
        //개인 정보 받아오는 부분
        
    },
}
</script>

<style>

</style>