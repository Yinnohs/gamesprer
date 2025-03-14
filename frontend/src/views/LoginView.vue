<script setup lang="ts">
import LoginForm from '@/components/login/LoginForm.vue'
import { onMounted } from 'vue'
import { useUserStore } from '@/user/store/user.store';
import { useLink } from 'vue-router'
import { useNotificationStore } from '@/notification/notification.store';

const { userBootstrap, userRef } = useUserStore()
const { connect } = useNotificationStore()
const link = useLink({
  to: '/find'
})

onMounted(()=>{
  if(userBootstrap()){
    if(userRef && userRef?.id !== undefined){
      connect(userRef.id)
    }
    link.navigate()
  }
})
</script>

<template>
  <LoginForm />
</template>
