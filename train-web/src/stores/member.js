import {defineStore} from "pinia";
import {ref} from 'vue'

export const useMemberStore = defineStore('Member', () => {
    const token = ref('')
    const member = ref({})

    const setMember = (memberValue) => {
      member.value = memberValue
      token.value = member.value.token
    }
    const removeMember = () => {
      member.value = {}
    }
    return {
      member,
      setMember,
      removeMember,
      token
    }
  }, {
    persist: true // 持久化
  }
)
