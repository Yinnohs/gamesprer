<script setup lang="ts">
import LoginForm from '@/components/login/LoginForm.vue'
import {onMounted} from 'vue'
import { useUserStore } from '@/user/store/user.store';
import {useLink} from 'vue-router'

const {userBootstrap} = useUserStore()
const link = useLink({
  to: '/find'
})

onMounted(()=>{
  if(userBootstrap()){
    link.navigate()
  }
})
</script>

<template>
  <LoginForm />
</template>
