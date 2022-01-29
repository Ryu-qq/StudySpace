import { 
    fetchNewsList,
    fetchJobsList,
    fetchAskList,
    fetchUserInfo,
    fetchedItem,

} from '../api/index.js';

export default{
    FETCH_NEWS({commit}){
        fetchNewsList()
        .then(({ data }) =>{
            commit('SET_NEWS', data);
            console.log(data);
        })
        .catch(error =>{
            console.log(error);
        })

    },

    FETCH_JOBS({commit}){
        fetchJobsList()
        .then(({ data }) =>{
            commit('SET_JOBS', data);
            console.log(data);
        })
        .catch(error =>{
            console.log(error);
        })
    },

    FETCH_ASK({commit}){
        fetchAskList()
        .then(({ data }) => {
            commit('SET_ASK', data);
        })
        .catch(error =>{
            console.log(error);
        })
    },
    
    FETCH_USER({commit}, name){
        fetchUserInfo(name)
        .then(({data}) => {
            commit('SET_USER', data);
        })
        .catch(error =>{
            console.log(error);
        })
    },
    FETCH_ITEM({commit}, id){
        fetchedItem(id)
        .then(({data}) => {
            commit('SET_ITEM', data);
        })
        .catch(error =>{
            console.log(error);
        })
    },

}