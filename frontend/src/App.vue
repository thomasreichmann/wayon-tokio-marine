<script setup>
</script>

<template>
  <header>
    <div class="wrapper">
      <h1>Header</h1>
    </div>
  </header>

  <main>
    <h2>Main</h2>
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
