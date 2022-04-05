<template>
<v-container fluid>
    <v-row justify="end">
        <v-col cols="auto" align-self="center">
            <v-btn class="primary " @click="dialog2 = true">책 등록</v-btn>
            <v-btn class="secondary ml-3" @click="getBook">초기화</v-btn>
        </v-col>
    </v-row>
    <v-row>
        <v-col>
            <v-data-table :headers="headers" :options.sync="options" :items="books" item-key="orderIdx" hide-default-footer :loading="loading" no-data-text="검색된 자료가 없습니다" :footer-props="{'items-per-page-options': [5, 10, 15]}">
                <template #[`item.price`]="{item}">
                    {{ AddComma(item.price) }}원
                </template>
                <template #[`item.createdDate`]="{item}">
                    <div v-if="item.modifiedDate != null">
                        <DateDisplay :regDate="item.createdDate" />
                    </div>
                </template>
                <template #[`item.modifiedDate`]="{item}">
                    <div v-if="item.modifiedDate != null">
                        <DateDisplay :regDate="item.modifiedDate" />
                    </div>
                </template>
                <template v-slot:[`item.change`]="{item}">
                    <v-btn class="primary" @click.stop="selectItem(item), dialog = true">
                        수정
                    </v-btn>
                </template>
            </v-data-table>
        </v-col>
    </v-row>
    <v-dialog v-model="dialog" persistent width="600px">
        <v-card class="pa-2">
            <v-form ref="form">
                <v-simple-table>
                    <template slot="default">
                        <thead>
                            <tr>
                                <th class="text-h5" colspan="2">수정</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-h6">번호</td>
                                <td>
                                    <v-text-field v-model="bookInfo.id" hide-details="auto" readonly></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-h6">제목</td>
                                <td>
                                    <v-text-field v-model="bookInfo.title" :rules="rules.title" hide-details="auto"></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-h6">가격</td>
                                <td>
                                    <v-text-field v-model="bookInfo.price" :rules="rules.price" hide-details="auto"></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-h6">저자</td>
                                <td>
                                    <v-text-field v-model="bookInfo.author" :rules="rules.length" hide-details="auto"></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-h6">출판사</td>
                                <td>
                                    <v-text-field v-model="bookInfo.publisher" :rules="rules.length" hide-details="auto"></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-h6">추가 날짜</td>
                                <td>
                                    <v-text-field v-model="bookInfo.createdDate" hide-details="auto" readonly></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-h6">수정 날짜</td>
                                <td>
                                    <v-text-field v-model="bookInfo.modifiedDate" hide-details="auto" readonly></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <v-row justify="space-between">
                                        <v-col cols="auto">
                                            <v-btn class="error" @click="deleteBook">삭제</v-btn>
                                        </v-col>
                                        <v-col cols="auto">
                                            <v-btn class="primary" @click="updateBook">수정</v-btn>
                                            <v-btn class="error ml-3" @click="dialog = false; bookInfo = {}">취소</v-btn>
                                        </v-col>
                                    </v-row>
                                </td>
                            </tr>
                        </tbody>
                    </template>
                </v-simple-table>
            </v-form>
        </v-card>
    </v-dialog>
    <v-dialog v-model="dialog2" persistent max-width="500">
        <v-card>
            <v-form ref="form">
                <v-simple-table>
                    <template slot="default">
                        <thead>
                            <tr>
                                <th class="text-h5" colspan="2">책 등록</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td> 제목 </td>
                                <td>
                                    <v-text-field v-model="book.title" :rules="rules.title" outlined hide-details="auto" dense required></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td> 가격 </td>
                                <td>
                                    <v-text-field v-model="book.price" :rules="rules.price" outlined hide-details="auto" dense required></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td> 저자 </td>
                                <td>
                                    <v-text-field v-model="book.author" :rules="rules.length" outlined hide-details="auto" dense></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td> 출판사 </td>
                                <td>
                                    <v-text-field v-model="book.publisher" :rules="rules.length" outlined hide-details="auto" dense></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <v-row justify="end">
                                        <v-col cols="auto">
                                            <v-btn class="primary" @click="insertBook">등록</v-btn>
                                            <v-btn class="error ml-3" @click="bookReset">취소</v-btn>
                                        </v-col>
                                    </v-row>
                                </td>
                            </tr>
                        </tbody>
                    </template>
                </v-simple-table>
            </v-form>
        </v-card>
    </v-dialog>
    <v-dialog v-model="alertDialog" max-width="350">
        <v-alert class="mb-0" :type="alertType">
            <span v-html="alertMessage"></span>
        </v-alert>
    </v-dialog>
</v-container>
</template>

<script>
import axios from 'axios'
import DateDisplay from '@/components/DateDisplay.vue'
export default {
    components: {
        DateDisplay,
    },
    data() {
        return {
            alertDialog: false,
            alertMessage: '',
            alertType: '',
            books: [],
            loading: false,
            options: {
                itemsPerPage: 50,
            },
            headers: [{
                text: '번호',
                value: 'id',
                divider: true,
                align: 'center',
                width: '5%',
            }, {
                text: '제목',
                value: 'title',
                divider: true,
                align: 'center',
                width: '20%',
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
                width: '15%',
            }, {
                text: '출판사',
                value: 'publisher',
                divider: true,
                align: 'center',
                width: '15%',
            }, {
                text: '추가시간',
                value: 'createdDate',
                divider: true,
                align: 'center',
                width: '10%',
            }, {
                text: '수정시간',
                value: 'modifiedDate',
                divider: true,
                align: 'center',
                width: '10%',
            }, {
                text: '수정',
                value: 'change',
                align: 'center',
                width: '10%',
                sortable: false,
            },],

            searches: [{
                text: '번호',
                value: 'id',
            }, {
                text: '제목',
                value: 'title',
            }, {
                text: '가격',
                value: 'price',
            }, {
                text: '저자',
                value: 'author',
            }, {
                text: '출판사',
                value: 'publisher',
            }, {
                text: '추가 날짜',
                value: 'createdDate',
            }, {
                text: '수정 날짜',
                value: 'modifiedDate',
            }, ],
            search: 'id',
            searchWord1: '',
            searchWord2: '',

            menu1: false,
            menu2: false,

            dialog: false,
            dialog2: false,
            bookInfo: {},

            book:{
                title: null,
                price: null,
                author: null,
                publisher: null,
            },
            bookTemp:'',
            rules: {
                title: [
                    v => !!v || '제목은 필수 입력사항입니다.',
                    v => !(v && v.length > 255) || '255자까지 입력가능합니다',
                ],
                price: [v => !!v || '가격은 필수 입력사항입니다.',
                    v => /^[0-9]+$/.test(v) || '가격은 숫자만 입력 가능합니다',
                    v => v < 2147483647 || '숫자 범위를 벗어났습니다',
                ],
                length: [v => !(v && v.length > 255) || '255자까지 입력가능합니다',],
            },
            
        }
    },
    methods: {
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return `${num}`.toString().replace(regexp, ",");
        },
        getBook() {
            this.loading = true;
            axios.get(`/api/v1/posts`)
            .then(res => {
                this.books = res.data;
            }).catch((err) => {
                this.books = [];
                console.log(err);
            }).finally(
                this.loading = false
            )
        },
        selectItem(item) {
            this.bookInfo = {
                id: item.id,
                title: item.title,
                price: item.price,
                author: item.author,
                publisher: item.publisher,
                createdDate: item.createdDate,
                modifiedDate: item.modifiedDate,
            }
            this.bookTemp = this.bookInfo;
        },
        insertBook(){
            let validate = this.$refs.form.validate();
            if(validate){
                axios.put(`/api/v1/posts`, this.book)
                .then(res => {
                    this.bookReset();
                    this.alertDialog = true;
                    this.alertType = 'success';
                    this.alertMessage = '등록 성공';
                    this.getBook();
                    console.log(res);
                });
            }
        },
        updateBook(){
            let validate = this.$refs.form.validate();
            if(validate){
                axios.put(`/api/v1/posts/${this.bookInfo.id}`, this.bookInfo)
                .then(res => {
                    this.dialog = false;
                    this.bookInfo = {};
                    this.alertDialog = true;
                    this.alertType = 'success';
                    this.alertMessage = '수정 성공';
                    this.getBook();
                    console.log(res);
                });
            }
        },
        deleteBook(){
            axios.delete(`/api/v1/posts/${this.bookInfo.id}`)
            .then(res => {
                this.dialog = false;
                this.bookInfo = {};
                this.alertDialog = true;
                this.alertType = 'success';
                this.alertMessage = '삭제 성공';
                this.getBook();
                console.log(res);
            })
        },
        bookReset() {
            this.dialog2 = false;
            this.book = {
                title:null,
                price:null,
                author:null,
                publisher:null,
            }
        }
    },
    watch: { //변수 값이 변경될 때 연산을 처리하거나 변수 값에 따라 화면을 제어할 때 사용
        options: {
            handler() {
                this.getBook();
            },
            deep: true,
        },
    },
}
</script>

<style scoped>
</style>