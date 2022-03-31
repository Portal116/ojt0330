<template>
<v-container>
    <v-row justify="center" class="mt-1">
        <v-col cols="9">
            <v-data-table :headers="headers" :options.sync="options" :items="books" :server-items-length="totalContents" :loading="loading" item-key="id" class="elevation-1" disable-sort no-data-text="데이터가 없습니다." :footer-props="{'items-per-page-options': [5, 10, 15]}">

            </v-data-table>
        </v-col>
    </v-row>
</v-container>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            options: {},
            loading: false,
            books: [],
            headers: [{
                text: '책번호',
                value: 'id',
                divider: true,
                align: 'center',
                width: '10%',
            }, {
                text: '제목',
                value: 'title',
                divider: true,
                align: 'center',
                width: '35%',
            }, {
                text: '가격',
                value: 'price',
                divider: true,
                align: 'center',
                width: '15%',
            }, {
                text: '저자',
                value: 'author',
                divider: true,
                align: 'center',
                width: '20%',
            }, {
                text: '출판사',
                value: 'publisher',
                divider: true,
                align: 'center',
                width: '20%',
            }, ],
        }
    },
    methods: {
        getBookList() {
            this.loading = true;
            axios.get(`/api/v1/posts`)
            .then(res => {
                this.books = res.data;
            }).catch((err) => {
                console.log(err);
            }).finally(
                this.loading = false
            )
        },
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return `${num}`.toString().replace(regexp, ",");
        },
    },
    watch: {
        options: {
            handler() {
                this.getBookList();
            }
        }
    },
    mounted() {
        this.getBookList();
    }
}
</script>

<style>
</style>