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
import UserProfile from '../components/UserProfile.vue';
import ShowManageMovie from '../components/ShowManageMovie.vue';
import ViewMovieAddData from '../components/ViewMovieAddData.vue';
import MoviePics from '../components/MoviePics.vue';
import SearchPayment from "../components/SearchPayment.vue";
import InformPaymentId from "../components/InformPaymentId.vue";
import TicketData from  "../components/TicketData.vue";
import FoodanddrinkData from  "../components/FoodanddrinkData.vue";


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
            path:'/userhome/:id',
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
        },
        {
            path: '/profile',
            component: UserProfile
        },
        {
            path: '/showmm',
            component: ShowManageMovie
        },
        {
            path: '/moviepics',
            component: MoviePics
        },
        {
            path: '/viewmovieadddata',
            component: ViewMovieAddData
        },
        {
            path: '/informPaymentId',
            component: InformPaymentId
        },
        {
            path: '/searchPayment',
            component: SearchPayment
        },
        {
            path: '/TicketData',
            component: TicketData
        },
        {
            path: '/FoodanddrinkData',
            component: FoodanddrinkData
        }
    ]
});