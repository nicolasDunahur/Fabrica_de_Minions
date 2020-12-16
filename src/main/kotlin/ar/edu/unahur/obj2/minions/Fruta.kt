package ar.edu.unahur.obj2.minions

interface Fruta {
    var energia : Int
}

object Banana : Fruta{
    override var energia = 10
}

object Manzana :Fruta{
    override var energia = 5
}

object Uva: Fruta {
    override var energia = 1
}