<template>
    <div>
        <ul class ="news-list">
            <li v-for="item in listItems"  v-bind:key=item.id class="post">
                <!-- 점수 영역 -->
                <div class ="point">
                    {{item.points}}
                </div>
                
                <div>
                    <p class = 'news-title'>
                        
                        <!-- 해당 페이지로 연결 -->
                        <template v-if="item.domain">
                            <a v-bind:href="item.url"> {{ item.title }} </a>
                        </template>
                        
                        <!-- 상세 페이지로 연결   -->
                        <template v-else>
                            <router-link v-bind:to ="`/item/${item.id}`">
                                {{item.title}}
                            </router-link>
                        </template>
                    </p>
                    <small class = "link-text">  {{ item.time_ago }} by
                        <!-- item.user 가 있으면 밑에 링크  -->
                        <router-link v-if="item.user"
                            v-bind:to="`/user/${item.user}`" class = "link-text"> {{item.user}} 
                        </router-link>

                        <!-- 없으면 링크 도메인으로 -->
                        <a :href="item.url" v-else> {{item.domain}} </a>
                    </small>
                </div>
            </li>
        </ul>
    </div>
  
</template>

<script>
export default {

    created(){
        const name = this.$route.name;
        if(name === 'news'){
            this.$store.dispatch('FETCH_NEWS');
        }else if(name === 'ask'){
            this.$store.dispatch('FETCH_ASK');
        }else if(name === 'jobs'){
            this.$store.dispatch('FETCH_JOBS');
        }
    },

    computed:{
        listItems() {
        const name = this.$route.name;
        if(name ==='news'){
            return this.$store.state.news;
        }else if(name ==='ask'){
            return this.$store.state.ask;
        }else (name ==='jobs')
            return this.$store.state.jobs;
        }
    }

}
</script>

<style>

.news-list{
    margin:0;
    padding: 0;
}
.post{
    list-style: none;
    display: flex;
    align-items: center;
    border-bottom: 1px solid #eee;
}
.point{
    width: 80px;
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #42b883
}
.news-title{
    margin: 0;
}
.link-text{
    color: #828282;
}
    
</style>