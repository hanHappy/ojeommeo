import type { RouteRecordRaw, RouterScrollBehavior } from 'vue-router';
import { createRouter, createWebHistory } from 'vue-router';
import DefaultLayout from '@/layouts/Default.vue';
// import Home from '@/pages/Home.vue';

const scrollBehavior: RouterScrollBehavior = (to, from, savedPosition) => {
  if (savedPosition) {
    return savedPosition;
  }
  return { top: 0 };
};

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: DefaultLayout,
    children: [
      {
        path: 'sign-in',
        name: 'SignIn',
        component: () => import('@/views/SignIn.vue'),
      },
      // {
      //   path: '',
      //   name: 'Home',
      //   component: Home,
      // },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior,
  routes,
});

export default router;
