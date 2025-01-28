<script setup lang="ts">
import AppLink from '../link/AppLink.vue'
</script>

<template>
  <section
    class="relative h-screen w-screen flex flex-col items-center justify-center text-center text-white"
  >
    <div class="video-docker absolute top-0 left-0 w-full h-full overflow-hidden">
      <video
        class="min-w-full min-h-full absolute object-cover"
        src="https://cdn.pixabay.com/video/2021/03/06/67116-521253275_large.mp4"
        type="video/mp4"
        autoplay
        muted
        loop
      ></video>
    </div>
    <div class="video-content space-y-2 z-10">
      <h1
        class="text-center font-light text-8xl text-bold bg-gradient-to-r from-teal-50 to-teal-400 bg-clip-text text-transparent"
      >
        Gamesprer
      </h1>

      <h3 class="text-2xl text-zinc-700">Comparador de precios</h3>
      <AppLink />
    </div>
  </section>
</template>

<style>
.video-docker video {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.video-docker::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.6);
  z-index: 1;
}
</style>
