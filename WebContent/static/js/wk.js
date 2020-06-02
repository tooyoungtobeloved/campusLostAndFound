import TWEEN from "../lib/tween-es.js";

const o = { x: 0, y: 0 };
const o1 = { w: 10 };
new TWEEN.Tween(o).to({ x: 10, y: 10 }, 2000).start();
new TWEEN.Tween(o1)
  .easing(TWEEN.Easing.Bounce.InOut)
  .to({ w: 1000 }, 2000)
  .start();
class RandomColorPainter {
  // 可以获取的css属性，先写在这里
  // 我这里定义宽高和间隔，从css获取
  static get inputProperties() {
    return ["--w", "--h", "--spacing", "--m"];
  }
  /**
   * 绘制函数paint，最主要部分
   * @param {PaintRenderingContext2D} ctx 类似canvas的ctx
   * @param {PaintSize} PaintSize 绘制范围大小(px) { width, height }
   * @param {StylePropertyMapReadOnly} props 前面inputProperties列举的属性，用get获取
   */
  paint(ctx, PaintSize, props) {
    TWEEN.update();
    const _w = props.get("--w");
    const _h = props.get("--h");
    const _spacing = props.get("--spacing");
    const w = (_w && +_w[0].trim()) || 30;
    const h = (_h && +_h[0].trim()) || 30;
    const spacing = +(_spacing && _spacing[0].trim()) || 10;
    for (let x = 0; x < PaintSize.width / w; x++) {
      for (let y = 0; y < PaintSize.height / h; y++) {
        let rgb = Math.random()
          .toString(16)
          .slice(2, 8);
        ctx.fillStyle = `#${rgb}`;
        ctx.beginPath();
        ctx.rect(x * (w + spacing), y * (h + spacing), w, h);
        ctx.fill();
      }
    }
    // const rgb = Math.random()
    //   .toString(16)
    //   .slice(2, 8);
    // ctx.fillStyle = `#${rgb}`;
    // ctx.beginPath();
    // ctx.rect(o.x * (w + spacing), o.y * (h + spacing), o1.w, h);
    // ctx.fill();
  }
}

registerPaint("randomcolor", RandomColorPainter);
