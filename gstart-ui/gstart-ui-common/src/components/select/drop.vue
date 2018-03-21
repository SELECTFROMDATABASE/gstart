<template>
    <div class="gs-select-dropdown">
      <ul class="gs-select-dropdown-list">
        <li class="gs-select-item gs-select-item-selected">测试111</li>
        <li class="gs-select-item gs-select-item-disabled">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <li class="gs-select-item">测试111</li>
        <gs-option-group></gs-option-group>
      </ul>
    </div>
</template>

<script>
    import GsOptionGroup from "./option-group";

    export default {
      components: {GsOptionGroup},
      name: "gs-drop"
    }
</script>

<style>

</style>
