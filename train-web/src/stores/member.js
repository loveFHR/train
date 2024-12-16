import {defineStore} from "pinia";
import {ref} from 'vue'

export const useMemberStore = defineStore('Member', () => {

    const member = ref({})

    const setMember = (memberValue) => {
      member.value = memberValue
    }
    const removeMember = () => {
      member.value = {}
    }
    return {
      member,
      setMember,
      removeMember
    }
  }, {
    persist: true // 持久化
  }
)
