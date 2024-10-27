<script setup>
defineProps({
  variant: {
    default: 'primary',
    validator: (value) => ['primary', 'void', 'outline'].includes(value)
  },
  size: {
    default: 'md',
    validator: (value) => ['sm', 'md', 'lg'].includes(value)
  },
  disabled: {
    default: false
  }
})
</script>

<template>
  <button
    :class="[
      'rounded transition-colors',
      // 변형별 스타일
      {
        'bg-primary text-text-base hover:bg-primary-dark dark:bg-base dark:hover:bg-[#d6d6d6]': variant === 'primary',
        'bg-[#fff] hover:bg-[#eee] text-text-primary': variant === 'void',
        // 'border border-theme text-theme hover:bg-theme hover:text-white': variant === 'outline' TODO
      },
      // 크기별 스타일
      {
        'px-3 py-1.5 text-sm': size === 'sm',
        'px-4 py-2 text-base': size === 'md',
        'px-6 py-3 text-lg': size === 'lg'
      },
      // 비활성화 스타일
      { 'bg-disabled hover:bg-disabled opacity-50 cursor-not-allowed': disabled }
    ]"
    :disabled="disabled"
  >
    <slot></slot>
  </button>
</template>
