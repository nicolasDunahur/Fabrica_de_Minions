package ar.edu.unahur.obj2.minions

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  val obrero = Obrero()
  val laboratorio = Laboratorio()
  val soldado= Soldado()
  val limpiador = Limpiador()


  val empleadoBiclope = Biclope(obrero, 0)
  val empleadoCiclope = Ciclople(obrero, 50)

  val maquinaQuimica = ArreglarMaquina(mutableListOf("destornillador"), 5)

  repeat(2) {
    laboratorio.enviarTarea(empleadoCiclope, maquinaQuimica)
  }


  describe("1. minion come fruta para recuperar energia") {

    it("empleado come uva ") {
      empleadoCiclope.comerFruta(Uva)
      empleadoBiclope.comerFruta(Uva)

      empleadoCiclope.estamina.shouldBe(51)
      empleadoBiclope.estamina.shouldBe(1)
    }
    it("empleado come manzana") {
      empleadoCiclope.comerFruta(Manzana)
      empleadoBiclope.comerFruta(Manzana)

      empleadoCiclope.estamina.shouldBe(55)
      empleadoBiclope.estamina.shouldBe(5)
    }
    it("empleado como bananas") {
      empleadoCiclope.comerFruta(Banana)
      empleadoBiclope.comerFruta(Banana)

      empleadoCiclope.estamina.shouldBe(150)
      empleadoBiclope.estamina.shouldBe(10)
    }
  }

  describe("2. Experiencia que tiene los empleados por realizar tareas") {

    it("total de experiencia adquirida por el empleadoBiclope") {
        empleadoBiclope.experiencia().shouldBe(0)
     }
    it("experiencia del ciclope, realizo 2 tareas de reparacion") {
       empleadoCiclope.experiencia().shouldBe(40)
    }
  }

  describe(" Fuerza de los empleados"){

    val ciclopeObrero = Ciclople(obrero,50)

    it("fuerza de los empleados con rol obrero"){

      empleadoBiclope.fuerza().shouldBe(2)
      ciclopeObrero.fuerza().shouldBe(13)
    }
    it("fuerza de los empleados con rol Soldado"){
      laboratorio.asignarRol(ciclopeObrero,soldado)
      soldado.danioExtra = 4

      ciclopeObrero.fuerza().shouldBe(15)

    }
  }

  describe("3. Si el empleado PUEDE realizar una tarea") {

    describe("Reparacion") {
      val pc = ArreglarMaquina(mutableListOf("cd", "tester"), 5)
      val maquina = ArreglarMaquina(mutableListOf(), 25)

      it("empleado ciclope no puede realizar tarea de reparacion, no tiene la herramientas necesarias ") {
        empleadoCiclope.puedeRealizarTarea(pc).shouldBeFalse()
      }
      it("empleadoCiclope puede realizar tarea de raparacion ") {
        empleadoCiclope.puedeRealizarTarea(maquina).shouldBeTrue()
      }
    }

    describe("Defensa") {
      describe("si el empleado puede defender el sector") {
        val sectorA = Sector(true, false, 20)
        val defensa = DefenderSector(sectorA)
        val obreroBiclope = Biclope(obrero, 10)
        val obreroCicople = Ciclople(obrero, 100)

        describe("El empleado no puede defender,ya que es LIMPIADOR") {
          laboratorio.asignarRol(empleadoBiclope, limpiador)
          it("no puede defender") {
            empleadoBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
        }

        describe("empleados OBRERO") {
          it("pueden defender el sector") {
            defensa.puedeSerRealizada(obreroBiclope).shouldBeFalse()
            defensa.puedeSerRealizada(obreroCicople).shouldBeTrue()
          }
        }

        describe("empleados SOLDADO") {
          laboratorio.asignarRol(empleadoBiclope, soldado)
          laboratorio.asignarRol(empleadoCiclope, soldado)

          it("el empleado no pude realizar la tarea de defender el sector") {
            obreroBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
          it("el empleado si puede realizar la tarea de defenser el sector ") {
            obreroCicople.puedeRealizarTarea(defensa).shouldBeTrue()
          }
        }
      }
    }
    describe("Limpieza"){
      val banio = Sector(true,false,100)
      val limpiarbanio = LimpiarSector(banio)
      val cocina = Sector(false,false,100)
      val limpiarCocina = LimpiarSector(cocina)

      it("Los limpiadores pueden limpiar siempre"){
        val MrMusculo = Biclope(limpiador,1)
        // limpiarbanio.puedeSerRealizada(MrMusculo).shouldBeTrue()
      }
      it("No se puede limpiar porque es grande y tiene poca estamina"){
        val obreroBiclope = Biclope(obrero, 3)
        limpiarbanio.puedeSerRealizada(obreroBiclope).shouldBeFalse()
      }
      it("No se puede limpiar porque es chico y no tiene estamina"){
        val obreroDebil = Biclope(obrero, 0)
        limpiarCocina.puedeSerRealizada(obreroDebil).shouldBeFalse()
      }
      it("Si se puede limpiar ya que tiene bastante estamina"){
        val obreroFuerte = Biclope(obrero, 4)
        limpiarbanio.puedeSerRealizada(obreroFuerte).shouldBeTrue()
      }
      it("Si se puede limpiar porque es chico"){
        val obreroMenosDebil = Biclope(obrero, 1)
        limpiarCocina.puedeSerRealizada(obreroMenosDebil).shouldBeTrue()
      }

    }
  }

  describe("4.Realizar tareas"){
    describe("Defender"){

      it("Se puede defender"){

      }
      it("No se puede defender y da error"){

      }

    }
    describe("Limpiar"){

      it("Se puede limpiar"){

      }
      it("No se puede limpiar y da error"){

      }

    }
    describe("Arreglar"){

      it("Se puede arreglar"){

      }
      it("No se puede arreglar y da error"){

      }

    }




  }



  describe("5. nuevo rol capataz"){
    val capataz = Capataz()
    val empleadoCapataz = Ciclople(capataz,100)
    capataz.subAlternos = mutableListOf(empleadoBiclope,empleadoCiclope)
  }

  describe("6. Laboratorio - esta en orden"){
    val sector1 = Sector(true,true,0)
    val sector2 = Sector(true,true,0)
    val sector3 = Sector(true,true,0)

    val obreroBiclope = Biclope(obrero, 10)
    val obreroCicople = Ciclople(obrero,100)

    val laboratorioX= Laboratorio()
    laboratorioX.sectores = mutableListOf<Sector>(sector1,sector2,sector3)
    laboratorioX.empleados = mutableListOf<Minion>(obreroBiclope,obreroCicople)

    it("Todes contentes, sin amenazas y limpio"){
      laboratorioX.enOrden().shouldBeTrue()
    }
    it("Infeliz, sin amenazas y limpio"){
      obreroBiclope.estamina = 8
      laboratorioX.enOrden().shouldBeFalse()
    }
  }
  describe("7. Laboratorio - Jornada Laboral"){
    val sector1 = Sector(true,false,4)
    val sector2 = Sector(true,false,4)
    val sector3 = Sector(true,false,0)

    val obreroBiclope = Biclope(obrero, 10)
    val obreroCicople = Ciclople(obrero,100)
    val obreroInutil = Ciclople(obrero ,1)

    val defender1 = DefenderSector(sector1)
    val defender2 = DefenderSector(sector2)
    val repararPc = ArreglarMaquina(mutableListOf("cd", "tester"), 5)
    val limpiar3 = LimpiarSector(sector3)
    val repararReactor = ArreglarMaquina(mutableListOf("escabadientes"), 500000)

    val laboratorioX= Laboratorio()

    it("Realizar todas las tareas"){
      laboratorioX.sectores = mutableListOf<Sector>(sector1,sector2,sector3)
      laboratorioX.empleados = mutableListOf<Minion>(obreroBiclope,obreroCicople)
      laboratorioX.tareasPendientes = mutableListOf<Tarea>(defender1,defender2,repararPc,limpiar3)
      laboratorioX.jonadaLaboral()
      laboratorioX.tareasPendientes.shouldBeEmpty()

    }
    it("No se puede realizar debido a que no hay tareas"){
      laboratorioX.empleados = mutableListOf<Minion>(obreroBiclope,obreroCicople)

      shouldThrowAny {
        laboratorioX.jonadaLaboral()
      }
    }
    it("Nadie puede realizar las tareas") {
      laboratorioX.empleados = mutableListOf<Minion>(obreroInutil)
      laboratorioX.tareasPendientes = mutableListOf<Tarea>(defender1, defender2, repararPc, limpiar3)

      shouldThrowAny {
        laboratorioX.jonadaLaboral()
      }
    }
    it("Quedan tareas sin resolver"){
      laboratorioX.sectores = mutableListOf<Sector>(sector1,sector2,sector3)
      laboratorioX.empleados = mutableListOf<Minion>(obreroBiclope,obreroCicople)
      laboratorioX.tareasPendientes = mutableListOf<Tarea>(defender1,defender2,repararPc,limpiar3,repararReactor)

      shouldThrowAny {
        laboratorioX.jonadaLaboral()
      }
      laboratorioX.jonadaLaboral()
      laboratorioX.tareasPendientes.size.shouldBe(1)
    }


  }



})

