import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layouts/TheLayout.vue";
import Home from '@/views/TheHome.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    return { top: 0 };
  },
  routes: [
    {
      path: "/",
      component: Layout,
      children: [
        {
          path: "",
          name: "Home",
          component: Home
        }
        // {
        //   path: "/signup",
        //   name: "SignUp",
        //   component: () => import("../views/SignUp.vue"),
        // },
        // {
        //   path: "/signin",
        //   name: "SignIn",
        //   component: () => import("../views/SignIn.vue"),
        // },
        // {
        //   path: "",
        //   name: "ChatRooms",
        //   component: () => import("../views/ChatRoomList.vue"),
        //   meta: { requiresAuth: true },
        // },
        // {
        //   path: "chatrooms/:id",
        //   name: "ChatRoom",
        //   component: () => import("../views/ChatRoom.vue"),
        //   meta: { requiresAuth: true },
        // },
        // {
        //   path: "create-chatroom",
        //   name: "CreateChatRoom",
        //   component: () => import("../views/CreateChatRoom.vue"),
        //   meta: { requiresAuth: true },
        // },
      ],

    },
  ]
})

export default router
