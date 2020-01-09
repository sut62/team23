import Vue from 'vue'
import Router from 'vue-router';
import UserLogin from '../components/UserLogin.vue';
import Register from '../components/Register.vue';
import EmployeeLogin from '../components/EmployeeLogin.vue';
import ManageMovie from '../components/ManageMovie.vue';
import HomeForEmployee from '../components/HomeForEmployee.vue';
import HomeForUser from '../components/HomeForUser.vue';
import Foodanddrik from '../components/Foodanddrink.vue';
import MovieAdd from '../components/MovieAdd.vue';
import TicketBookingUI from '../components/TicketBookingUI.vue';
import PostPayment from '../components/PostPayment.vue';
Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: UserLogin    
        },
        {
            path: '/employeelogin',
            component: EmployeeLogin
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/manageMovie',
            component: ManageMovie
        },
        {
            path: '/empoyeehome',
            component: HomeForEmployee
        },
        {
            path:'/userhome',
            component: HomeForUser
        },
        {
            path: '/foodanddrik',
            component: Foodanddrik
        },
        {
            path: '/movieadd',
            component: MovieAdd
        },
        {
            path: '/ticketbooking',
            component: TicketBookingUI
        },
        {
            path: '/postpayment',
            component: PostPayment
        }
        

    ]
});