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


  describe("minion  come fruta para recuperar energia") {



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
    describe("experiencia que tiene los empleados por realizar tareas") {


      it("total de experiencia adquirida por el empleadoBiclope") {
        empleadoBiclope.experiencia().shouldBe(0)

      }
      it("experiencia del ciclope, realizo 2 tareas de reparacion") {
        empleadoCiclope.experiencia().shouldBe(40)

      }
    }
    describe(" fuerza de los empleados"){

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

    describe("si lo empleados pueden realizar las tareas asignadas") {
      val pc = ArreglarMaquina(mutableListOf("cd", "tester"), 5)

      it("empleado ciclope no puede realizar tarea de reparacion, no tiene la herramientas necesarias ") {
        empleadoCiclope.puedeRealizarTarea(pc).shouldBeFalse()
      }

      val maquina = ArreglarMaquina(mutableListOf(), 25)

      it("empleadoCiclope puede realizar tarea de raparacion ") {

        empleadoCiclope.puedeRealizarTarea(maquina).shouldBeTrue()
      }

      describe("si el empleado puede defender el sector") {
        val sectorA = Sector(true,false,20)
        val defensa = DefenderSector(sectorA)
        val obreroBiclope = Biclope(obrero , 10)
        val obreroCicople = Ciclople(obrero,100)

        describe(" el empleado no puede realizar la tarea ,el empleado ahora tiene rol limpiador"){

          laboratorio.asignarRol(empleadoBiclope,limpiador)

          it("no puede defender"){
            empleadoBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
        }
        describe("empleados con rol de Obreros"){

          it("pueden defender el sector"){

            defensa.puedeSerRealizada(obreroBiclope).shouldBeFalse()

            defensa.puedeSerRealizada(obreroCicople).shouldBeTrue()
          }
        }
        describe("empleados con rol soldado"){
          laboratorio.asignarRol(empleadoBiclope,soldado)
          laboratorio.asignarRol(empleadoCiclope,soldado)

          it( "el empleado no pude realizar la tarea de defender el sector"){
            obreroBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
          it("el empleado si puede realizar la tarea de defenser el sector "){

            obreroCicople.puedeRealizarTarea(defensa).shouldBeTrue()
          }
        }
      }
      val capataz = Capataz()
      describe("nuevo rol capataz"){
        val empleadoCapataz = Ciclople(capataz,100)
        capataz.subAlternos = mutableListOf(empleadoBiclope,empleadoCiclope)



      }

    }

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

      //laboratorioX.tareasPendientes.size.shouldBe(1)
      shouldThrowAny {
        laboratorioX.jonadaLaboral()
      }
    }


  }



})

