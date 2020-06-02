/** @license zlib.js 2012 - imaya [ https://github.com/imaya/zlib.js ] The MIT License */ (function() {
  "use strict";
  var l = void 0,
    aa = this;
  function r(c, d) {
    var a = c.split("."),
      b = aa;
    !(a[0] in b) && b.execScript && b.execScript("var " + a[0]);
    for (var e; a.length && (e = a.shift()); )
      !a.length && d !== l ? (b[e] = d) : (b = b[e] ? b[e] : (b[e] = {}));
  }
  var t =
    "undefined" !== typeof Uint8Array &&
    "undefined" !== typeof Uint16Array &&
    "undefined" !== typeof Uint32Array &&
    "undefined" !== typeof DataView;
  function v(c) {
    var d = c.length,
      a = 0,
      b = Number.POSITIVE_INFINITY,
      e,
      f,
      g,
      h,
      k,
      m,
      n,
      p,
      s,
      x;
    for (p = 0; p < d; ++p) c[p] > a && (a = c[p]), c[p] < b && (b = c[p]);
    e = 1 << a;
    f = new (t ? Uint32Array : Array)(e);
    g = 1;
    h = 0;
    for (k = 2; g <= a; ) {
      for (p = 0; p < d; ++p)
        if (c[p] === g) {
          m = 0;
          n = h;
          for (s = 0; s < g; ++s) (m = (m << 1) | (n & 1)), (n >>= 1);
          x = (g << 16) | p;
          for (s = m; s < e; s += k) f[s] = x;
          ++h;
        }
      ++g;
      h <<= 1;
      k <<= 1;
    }
    return [f, a, b];
  }
  function w(c, d) {
    this.g = [];
    this.h = 32768;
    this.d = this.f = this.a = this.l = 0;
    this.input = t ? new Uint8Array(c) : c;
    this.m = !1;
    this.i = y;
    this.r = !1;
    if (d || !(d = {}))
      d.index && (this.a = d.index),
        d.bufferSize && (this.h = d.bufferSize),
        d.bufferType && (this.i = d.bufferType),
        d.resize && (this.r = d.resize);
    switch (this.i) {
      case A:
        this.b = 32768;
        this.c = new (t ? Uint8Array : Array)(32768 + this.h + 258);
        break;
      case y:
        this.b = 0;
        this.c = new (t ? Uint8Array : Array)(this.h);
        this.e = this.z;
        this.n = this.v;
        this.j = this.w;
        break;
      default:
        throw Error("invalid inflate mode");
    }
  }
  var A = 0,
    y = 1,
    B = { t: A, s: y };
  w.prototype.k = function() {
    for (; !this.m; ) {
      var c = C(this, 3);
      c & 1 && (this.m = !0);
      c >>>= 1;
      switch (c) {
        case 0:
          var d = this.input,
            a = this.a,
            b = this.c,
            e = this.b,
            f = d.length,
            g = l,
            h = l,
            k = b.length,
            m = l;
          this.d = this.f = 0;
          if (a + 1 >= f) throw Error("invalid uncompressed block header: LEN");
          g = d[a++] | (d[a++] << 8);
          if (a + 1 >= f)
            throw Error("invalid uncompressed block header: NLEN");
          h = d[a++] | (d[a++] << 8);
          if (g === ~h)
            throw Error("invalid uncompressed block header: length verify");
          if (a + g > d.length) throw Error("input buffer is broken");
          switch (this.i) {
            case A:
              for (; e + g > b.length; ) {
                m = k - e;
                g -= m;
                if (t) b.set(d.subarray(a, a + m), e), (e += m), (a += m);
                else for (; m--; ) b[e++] = d[a++];
                this.b = e;
                b = this.e();
                e = this.b;
              }
              break;
            case y:
              for (; e + g > b.length; ) b = this.e({ p: 2 });
              break;
            default:
              throw Error("invalid inflate mode");
          }
          if (t) b.set(d.subarray(a, a + g), e), (e += g), (a += g);
          else for (; g--; ) b[e++] = d[a++];
          this.a = a;
          this.b = e;
          this.c = b;
          break;
        case 1:
          this.j(ba, ca);
          break;
        case 2:
          for (
            var n = C(this, 5) + 257,
              p = C(this, 5) + 1,
              s = C(this, 4) + 4,
              x = new (t ? Uint8Array : Array)(D.length),
              S = l,
              T = l,
              U = l,
              u = l,
              M = l,
              F = l,
              z = l,
              q = l,
              V = l,
              q = 0;
            q < s;
            ++q
          )
            x[D[q]] = C(this, 3);
          if (!t) {
            q = s;
            for (s = x.length; q < s; ++q) x[D[q]] = 0;
          }
          S = v(x);
          u = new (t ? Uint8Array : Array)(n + p);
          q = 0;
          for (V = n + p; q < V; )
            switch (((M = E(this, S)), M)) {
              case 16:
                for (z = 3 + C(this, 2); z--; ) u[q++] = F;
                break;
              case 17:
                for (z = 3 + C(this, 3); z--; ) u[q++] = 0;
                F = 0;
                break;
              case 18:
                for (z = 11 + C(this, 7); z--; ) u[q++] = 0;
                F = 0;
                break;
              default:
                F = u[q++] = M;
            }
          T = t ? v(u.subarray(0, n)) : v(u.slice(0, n));
          U = t ? v(u.subarray(n)) : v(u.slice(n));
          this.j(T, U);
          break;
        default:
          throw Error("unknown BTYPE: " + c);
      }
    }
    return this.n();
  };
  var G = [16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15],
    D = t ? new Uint16Array(G) : G,
    H = [
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      13,
      15,
      17,
      19,
      23,
      27,
      31,
      35,
      43,
      51,
      59,
      67,
      83,
      99,
      115,
      131,
      163,
      195,
      227,
      258,
      258,
      258
    ],
    I = t ? new Uint16Array(H) : H,
    J = [
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      0,
      1,
      1,
      1,
      1,
      2,
      2,
      2,
      2,
      3,
      3,
      3,
      3,
      4,
      4,
      4,
      4,
      5,
      5,
      5,
      5,
      0,
      0,
      0
    ],
    K = t ? new Uint8Array(J) : J,
    L = [
      1,
      2,
      3,
      4,
      5,
      7,
      9,
      13,
      17,
      25,
      33,
      49,
      65,
      97,
      129,
      193,
      257,
      385,
      513,
      769,
      1025,
      1537,
      2049,
      3073,
      4097,
      6145,
      8193,
      12289,
      16385,
      24577
    ],
    da = t ? new Uint16Array(L) : L,
    ea = [
      0,
      0,
      0,
      0,
      1,
      1,
      2,
      2,
      3,
      3,
      4,
      4,
      5,
      5,
      6,
      6,
      7,
      7,
      8,
      8,
      9,
      9,
      10,
      10,
      11,
      11,
      12,
      12,
      13,
      13
    ],
    N = t ? new Uint8Array(ea) : ea,
    O = new (t ? Uint8Array : Array)(288),
    P,
    fa;
  P = 0;
  for (fa = O.length; P < fa; ++P)
    O[P] = 143 >= P ? 8 : 255 >= P ? 9 : 279 >= P ? 7 : 8;
  var ba = v(O),
    Q = new (t ? Uint8Array : Array)(30),
    R,
    ga;
  R = 0;
  for (ga = Q.length; R < ga; ++R) Q[R] = 5;
  var ca = v(Q);
  function C(c, d) {
    for (var a = c.f, b = c.d, e = c.input, f = c.a, g = e.length, h; b < d; ) {
      if (f >= g) throw Error("input buffer is broken");
      a |= e[f++] << b;
      b += 8;
    }
    h = a & ((1 << d) - 1);
    c.f = a >>> d;
    c.d = b - d;
    c.a = f;
    return h;
  }
  function E(c, d) {
    for (
      var a = c.f,
        b = c.d,
        e = c.input,
        f = c.a,
        g = e.length,
        h = d[0],
        k = d[1],
        m,
        n;
      b < k && !(f >= g);

    )
      (a |= e[f++] << b), (b += 8);
    m = h[a & ((1 << k) - 1)];
    n = m >>> 16;
    if (n > b) throw Error("invalid code length: " + n);
    c.f = a >> n;
    c.d = b - n;
    c.a = f;
    return m & 65535;
  }
  w.prototype.j = function(c, d) {
    var a = this.c,
      b = this.b;
    this.o = c;
    for (var e = a.length - 258, f, g, h, k; 256 !== (f = E(this, c)); )
      if (256 > f)
        b >= e && ((this.b = b), (a = this.e()), (b = this.b)), (a[b++] = f);
      else {
        g = f - 257;
        k = I[g];
        0 < K[g] && (k += C(this, K[g]));
        f = E(this, d);
        h = da[f];
        0 < N[f] && (h += C(this, N[f]));
        b >= e && ((this.b = b), (a = this.e()), (b = this.b));
        for (; k--; ) a[b] = a[b++ - h];
      }
    for (; 8 <= this.d; ) (this.d -= 8), this.a--;
    this.b = b;
  };
  w.prototype.w = function(c, d) {
    var a = this.c,
      b = this.b;
    this.o = c;
    for (var e = a.length, f, g, h, k; 256 !== (f = E(this, c)); )
      if (256 > f) b >= e && ((a = this.e()), (e = a.length)), (a[b++] = f);
      else {
        g = f - 257;
        k = I[g];
        0 < K[g] && (k += C(this, K[g]));
        f = E(this, d);
        h = da[f];
        0 < N[f] && (h += C(this, N[f]));
        b + k > e && ((a = this.e()), (e = a.length));
        for (; k--; ) a[b] = a[b++ - h];
      }
    for (; 8 <= this.d; ) (this.d -= 8), this.a--;
    this.b = b;
  };
  w.prototype.e = function() {
    var c = new (t ? Uint8Array : Array)(this.b - 32768),
      d = this.b - 32768,
      a,
      b,
      e = this.c;
    if (t) c.set(e.subarray(32768, c.length));
    else {
      a = 0;
      for (b = c.length; a < b; ++a) c[a] = e[a + 32768];
    }
    this.g.push(c);
    this.l += c.length;
    if (t) e.set(e.subarray(d, d + 32768));
    else for (a = 0; 32768 > a; ++a) e[a] = e[d + a];
    this.b = 32768;
    return e;
  };
  w.prototype.z = function(c) {
    var d,
      a = (this.input.length / this.a + 1) | 0,
      b,
      e,
      f,
      g = this.input,
      h = this.c;
    c &&
      ("number" === typeof c.p && (a = c.p),
      "number" === typeof c.u && (a += c.u));
    2 > a
      ? ((b = (g.length - this.a) / this.o[2]),
        (f = (258 * (b / 2)) | 0),
        (e = f < h.length ? h.length + f : h.length << 1))
      : (e = h.length * a);
    t ? ((d = new Uint8Array(e)), d.set(h)) : (d = h);
    return (this.c = d);
  };
  w.prototype.n = function() {
    var c = 0,
      d = this.c,
      a = this.g,
      b,
      e = new (t ? Uint8Array : Array)(this.l + (this.b - 32768)),
      f,
      g,
      h,
      k;
    if (0 === a.length)
      return t ? this.c.subarray(32768, this.b) : this.c.slice(32768, this.b);
    f = 0;
    for (g = a.length; f < g; ++f) {
      b = a[f];
      h = 0;
      for (k = b.length; h < k; ++h) e[c++] = b[h];
    }
    f = 32768;
    for (g = this.b; f < g; ++f) e[c++] = d[f];
    this.g = [];
    return (this.buffer = e);
  };
  w.prototype.v = function() {
    var c,
      d = this.b;
    t
      ? this.r
        ? ((c = new Uint8Array(d)), c.set(this.c.subarray(0, d)))
        : (c = this.c.subarray(0, d))
      : (this.c.length > d && (this.c.length = d), (c = this.c));
    return (this.buffer = c);
  };
  function W(c, d) {
    var a, b;
    this.input = c;
    this.a = 0;
    if (d || !(d = {}))
      d.index && (this.a = d.index), d.verify && (this.A = d.verify);
    a = c[this.a++];
    b = c[this.a++];
    switch (a & 15) {
      case ha:
        this.method = ha;
        break;
      default:
        throw Error("unsupported compression method");
    }
    if (0 !== ((a << 8) + b) % 31)
      throw Error("invalid fcheck flag:" + (((a << 8) + b) % 31));
    if (b & 32) throw Error("fdict flag is not supported");
    this.q = new w(c, {
      index: this.a,
      bufferSize: d.bufferSize,
      bufferType: d.bufferType,
      resize: d.resize
    });
  }
  W.prototype.k = function() {
    var c = this.input,
      d,
      a;
    d = this.q.k();
    this.a = this.q.a;
    if (this.A) {
      a =
        ((c[this.a++] << 24) |
          (c[this.a++] << 16) |
          (c[this.a++] << 8) |
          c[this.a++]) >>>
        0;
      var b = d;
      if ("string" === typeof b) {
        var e = b.split(""),
          f,
          g;
        f = 0;
        for (g = e.length; f < g; f++) e[f] = (e[f].charCodeAt(0) & 255) >>> 0;
        b = e;
      }
      for (var h = 1, k = 0, m = b.length, n, p = 0; 0 < m; ) {
        n = 1024 < m ? 1024 : m;
        m -= n;
        do (h += b[p++]), (k += h);
        while (--n);
        h %= 65521;
        k %= 65521;
      }
      if (a !== ((k << 16) | h) >>> 0) throw Error("invalid adler-32 checksum");
    }
    return d;
  };
  var ha = 8;
  r("Zlib.Inflate", W);
  r("Zlib.Inflate.prototype.decompress", W.prototype.k);
  var X = { ADAPTIVE: B.s, BLOCK: B.t },
    Y,
    Z,
    $,
    ia;
  if (Object.keys) Y = Object.keys(X);
  else for (Z in ((Y = []), ($ = 0), X)) Y[$++] = Z;
  $ = 0;
  for (ia = Y.length; $ < ia; ++$)
    (Z = Y[$]), r("Zlib.Inflate.BufferType." + Z, X[Z]);
}.call(window));
