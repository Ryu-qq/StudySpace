import axios  from 'axios';

//HTTP Request & Response와 관련 기본설정
const config ={
    baseUrl:'https://api.hnpwa.com/v0/'

}

//API 함수들을 정리
function fetchNewsList(){
    return axios.get(`${config.baseUrl}news/1.json`);

}

function fetchJobsList(){
    return axios.get(`${config.baseUrl}jobs/1.json`);

}
function fetchAskList(){
    return axios.get(`${config.baseUrl}ask/1.json`);

}

function fetchUserInfo(name){
    return axios.get(`${config.baseUrl}user/${name}.json`);
}

function fetchedItem(id){
    return axios.get(`${config.baseUrl}item/${id}.json`);
}

export {
    fetchNewsList,
    fetchAskList,
    fetchJobsList,
    fetchUserInfo,
    fetchedItem,
}