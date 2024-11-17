import type { RouteRecordRaw, RouterScrollBehavior } from 'vue-router';
import { createRouter, createWebHistory } from 'vue-router';
import DefaultLayout from '@/layouts/Default.vue';
import Home from '@/views/Home.vue';

// 인증 상태 체크 함수 (실제 구현에 맞게 수정 필요)
const isAuthenticated = (): boolean => {
  const token = localStorage.getItem('auth-token');
  return !!token;
};

const scrollBehavior: RouterScrollBehavior = (to, from, savedPosition) => {
  if (savedPosition) {
    return savedPosition;
  }
  return { top: 0 };
};

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    beforeEnter: (to, from, next) => {
      if (isAuthenticated()) {
        next({ name: 'Home' });
      } else {
        next();
      }
    },
  },
  {
    path: '/',
    component: DefaultLayout,
    children: [
      {
        path: '',
        name: 'Home',
        component: Home,
        meta: {
          requiresAuth: true,
        },
      },
    ],
  },
  // 404 페이지 처리
  {
    path: '/:pathMatch(.*)*',
    redirect: '/',
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior,
  routes,
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(r => r.meta.requiresAuth);

  if (requiresAuth && !isAuthenticated()) {
    // 인증이 필요한 페이지에 비로그인 상태로 접근 시
    next({
      name: 'Login',
      query: { redirect: to.fullPath }, // 로그인 후 원래 가려던 페이지로 리다이렉트
    });
  } else {
    next();
  }
});

export default router;
