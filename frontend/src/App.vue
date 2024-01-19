<script setup>
import TransactionForm from "@/components/TransactionForm.vue";
import {onMounted, ref} from "vue";
import TransactionTable from "@/components/TransactionTable.vue";
import {createTransaction as createTx, fetchTransactions} from "@/services/transactionService.js";

const transactions = ref([]);

async function createTransaction(newTransaction) {
  try {
    await createTx(newTransaction);
    transactions.value = await fetchTransactions();
  } catch (error) {
    console.error('Error in createTransaction:', error);
  }
}

onMounted(async () => {
  try {
    transactions.value = await fetchTransactions();
  } catch (error) {
    console.error('Error on mounted:', error);
  }
});
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
