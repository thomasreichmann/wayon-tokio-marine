<script setup>
import {defineProps} from 'vue';

defineProps({
  transactions: Array
});
</script>

<template>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Origin Account</th>
      <th>Destination Account</th>
      <th>Amount</th>
      <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="transaction in transactions" :key="transaction.id">
      <td>{{ transaction.id }}</td>
      <td>{{ transaction.originAccount }}</td>
      <td>{{ transaction.destinationAccount }}</td>
      <td>{{ transaction.amount }}</td>
      <td>{{ transaction.date }}</td>
    </tr>
    </tbody>
  </table>
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  box-shadow: 0 2px 6px rgba(255, 255, 255, 0.1);
  color: #fff;
}

table, th, td {
  border: 1px solid #333;
}

th, td {
  padding: 10px;
  text-align: left;
  background-color: #181818;
}

th {
  background-color: #202020;
}
</style>