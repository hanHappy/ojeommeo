import type { App } from 'vue';
import Input from '@/components/base/Input.vue';

export default {
  install (app: App) {
    app.component('Input', Input);
  },
};
