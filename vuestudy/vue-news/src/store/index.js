import Vue from 'vue';
import Vuex from 'vuex';
import mutations from './mutations.js';
import actions from './actions.js';

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        news:[],
        jobs:[],
        ask:[],
        user:{},
        item:[],
    },
    getters:{
        fetchedNews(state){
            return state.news;
        },
        fetchedAsk(state){
            return state.ask;
        },
        fetchedJobs(state){
            return state.jobs;
        },
        fetchedItem(state){
            return state.item;
        },
        fetcheduserInfo(state){
            return state.user;
        }
        

    },
    mutations,
    actions,
});