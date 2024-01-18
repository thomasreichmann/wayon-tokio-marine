<script setup>
import {ref} from 'vue';
import TransactionForm from "@/components/TransactionForm.vue";
import TransactionTable from "@/components/TransactionTable.vue";

const transactions = ref([
  {id: 1, originAccount: '123456', destinationAccount: '654321', amount: 100.00, date: '2022-01-01'},
  {
    id: 2,
    originAccount: '123456123456123456123456123456123456123456123456',
    destinationAccount: '654322',
    amount: 200.00,
    date: '2022-01-02'
  }
]);

function createTransaction(newTransaction) {
  transactions.value.push({...newTransaction, id: transactions.value.length + 1});
}
</script>

<template>
  <main>
    <TransactionForm @create="createTransaction"/>
    <TransactionTable :transactions="transactions"/>
  </main>
</template>
<style>
* {
  color-scheme: dark;
}

body, html {
  margin: 0;
  padding: 0;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  gap: 2em
}
</style>
