package fetch

import chisel3._

class Top extends Module {
  val io = IO(new Bundle {
    val exit = Output(Bool())
  })

  // CoreクラスとMemoryクラスをインスタンス化し、ModuleでHW化
  val core = Module(new Core())
  val memory = Module(new Memory())

  // coreとmemoryのIOはImemPortIOを反転したもの(<>は一括接続)
  core.io.imem <> memory.io.imem
  io.exit := core.io.exit
}
