import { ref, onMounted } from 'vue'

export function useDarkMode() {
  const isDark = ref(false)

  onMounted(() => {
    // 저장된 사용자 설정 또는 시스템 설정 확인
    isDark.value = localStorage.getItem('darkMode') === 'true'
    applyDarkMode()
  })

  const toggleDarkMode = () => {
    isDark.value = !isDark.value
    localStorage.setItem('darkMode', isDark.value)
    applyDarkMode()
  }

  const applyDarkMode = () => {
    document.documentElement.classList.toggle('dark', isDark.value)
  }

  return {
    isDark,
    toggleDarkMode
  }
}
