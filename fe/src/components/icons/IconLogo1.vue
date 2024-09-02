<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1006 649" fill="none" :width="widthAttr" :height="heightAttr"
    overflow="visible">
    <path
      d="M13 558.5L1.00138 558.318L0.837828 569.113L11.5557 570.413L13 558.5ZM457.85 612.434L466.193 621.058L459.294 600.521L457.85 612.434ZM432.724 636.739L424.381 628.115L403.06 648.739H432.724V636.739ZM992.85 558.5L994.284 570.414L1005.01 569.122L1004.85 558.317L992.85 558.5ZM545 612.434L543.565 600.52L536.522 620.926L545 612.434ZM569.349 636.739V648.739H598.356L577.827 628.247L569.349 636.739ZM24.9986 558.682L29.4987 261.65L5.5015 261.286L1.00138 558.318L24.9986 558.682ZM459.294 600.521L14.4443 546.587L11.5557 570.413L456.405 624.346L459.294 600.521ZM449.506 603.809L424.381 628.115L441.068 645.364L466.193 621.058L449.506 603.809ZM1004.85 558.317L1000.32 261.285L976.321 261.651L980.851 558.683L1004.85 558.317ZM546.435 624.347L994.284 570.414L991.415 546.586L543.565 600.52L546.435 624.347ZM536.522 620.926L560.871 645.232L577.827 628.247L553.478 603.941L536.522 620.926ZM432.724 648.739H569.349V624.739H432.724V648.739Z"
      :fill="strokeColor" />
    <path d="M401 76L329 2V437C329 455.8 435.333 489.5 488.5 504V141L450.5 119.5V278L429.5 253L401 278V76Z"
      fill="url(#paint0_linear_197_46)" />
    <path d="M309.5 112L221 62V452.5C267.5 487.5 406 507 366 496C334 487.2 315 467.667 309.5 459V112Z"
      fill="url(#paint1_linear_197_46)" />
    <path d="M201.5 172L116 127.5V466C194 506 394 537 320 520.5C260.8 507.3 216.333 487 201.5 478.5V172Z"
      fill="url(#paint2_linear_197_46)" />
    <path d="M309.5 112L221 62V452.5C267.5 487.5 406 507 366 496C334 487.2 315 467.667 309.5 459V112Z" class="" />
    <path d="M201.5 172L116 127.5V466C194 506 394 537 320 520.5C260.8 507.3 216.333 487 201.5 478.5V172Z" class="" />
    <path
      d="M528.5 146.5C560 124.167 634.8 67.9 682 21.5V462.5C660.333 480.833 603 518.3 547 521.5V539.75M793.5 194.5L895 144.5V494C747.4 551.6 601.5 560.667 547 558V539.75M547 539.75C573.167 540.5 659.1 529.2 793.5 478C793.5 251.2 793.5 117.167 793.5 80.5C762.333 98.8333 696.3 136.4 681.5 140"
      :stroke-width="strokeWidth" :stroke="strokeColor" />
    <defs>
      <linearGradient id="paint0_linear_197_46" x1="408.75" y1="2" x2="489" y2="606.5" gradientUnits="userSpaceOnUse">
        <stop stop-color="#6366F1" />
        <stop offset="1" stop-color="#0C0E65" />
      </linearGradient>
      <linearGradient id="paint1_linear_197_46" x1="244.581" y1="62" x2="244.581" y2="525.025"
        gradientUnits="userSpaceOnUse">
        <stop stop-color="#6366F1" />
        <stop offset="1" stop-color="#0C0E65" />
      </linearGradient>
      <linearGradient id="paint2_linear_197_46" x1="244.581" y1="62" x2="244.581" y2="525.025"
        gradientUnits="userSpaceOnUse">
        <stop stop-color="#6366F1" />
        <stop offset="1" stop-color="#0C0E65" />
      </linearGradient>
    </defs>
  </svg>
</template>

<script>
export default {
  name: 'Group10',

  props: {
    width: {
      type: String,
      required: false
    },

    height: {
      type: String,
      required: false
    },

    size: {
      type: String,
      required: false
    },
    strokeColor: {
      type: String
    },
    strokeWidth: {
      type: String
    }
  },

  computed: {
    widthAttr() {
      if (this.height) return undefined;
      if (this.size) return this.size;
      return this.width || "1006px";
    },

    heightAttr() {
      if (this.width) return undefined;
      if (this.size) return this.size;
      return this.height || "649px";
    }
  }
}
</script>
