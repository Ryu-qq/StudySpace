<template>
  <div> 
      <p>name :{{fetcheduserInfo.id}}</p>
      <p>karma :{{fetcheduserInfo.karma}}</p>
      <p>created: {{fetcheduserInfo.created}}</p>
  </div>
</template>

<script>

import {mapGetters} from 'vuex';


export default {
    computed:{
        ...mapGetters([
            'fetcheduserInfo'
        ]),
    },
    created(){
        const userName = this.$route.params.id;
        this.$store.dispatch('FETCH_USER',userName);

    }

}
</script>

<style>

</style>