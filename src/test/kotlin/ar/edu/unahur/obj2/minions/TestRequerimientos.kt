package ar.edu.unahur.obj2.minions

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class TestMinions : DescribeSpec({

    val obrero = Obrero()
    val soldado = Soldado()

    val empleado1 = Ciclople(obrero,50)
    val empleado2 = Biclope(soldado, 5)

    describe(" 1 los minion come fruta para recuperar energia"){
        it(" comer uva"){
            empleado1.comerFruta(Uva)
            empleado1.estamina.shouldBe(51)

            empleado2.comerFruta(Uva)
            empleado2.estamina.shouldBe(6)
        }
        it("comer manzana"){
            empleado1.comerFruta(Manzana)
            empleado1.estamina.shouldBe(55)

            empleado2.comerFruta(Manzana)
            empleado2.estamina.shouldBe(10)
        }
        it("comer banana, el empleado 2 es Biclope ose su maximo de estamina es 10"){
            empleado1.comerFruta(Banana)
            empleado1.estamina.shouldBe(150)

            empleado2.comerFruta(Banana)
            empleado2.estamina.shouldBe(10)
        }
    }
    val banio = Sector(true, false, 100)
    val cocina = Sector(false, false, 100)

    val limpiarbanio = LimpiarSector(banio)
    val repararMaquina = ArreglarMaquina(mutableListOf(), 25)
    val limpiarCocina = LimpiarSector(cocina)
    val repararPc = ArreglarMaquina(mutableListOf("cd","tester"), 5)



    describe("2 experiencia que tienen los empleados al realizar las tareas"){
        it("no tiene ninguna experiencia"){
            empleado1.experiencia().shouldBe(0)
        }
        it("el empledo tien experiencia" ){
            empleado1.puedeRealizarTarea(repararMaquina).shouldBeTrue()

            Laboratorio.enviarTarea(empleado1,repararMaquina )
            empleado1.experiencia().shouldBe(50)
        }

    }
    describe(" Fuerza de los empleados") {

        val ciclopeObrero = Ciclople(obrero, 50)

        it("fuerza de los empleados con rol obrero") {

            empleado2.fuerza().shouldBe(4)
            ciclopeObrero.fuerza().shouldBe(13)
        }
        it("fuerza de los empleados con rol Soldado") {
            Laboratorio.asignarRol(ciclopeObrero, soldado)
            soldado.danioExtra = 4

            ciclopeObrero.fuerza().shouldBe(15)

        }
    }
    describe("3. Si el empleado PUEDE realizar una tarea") {

        describe("Reparacion") {

            it("empleado ciclope no puede realizar tarea de reparacion, no tiene la herramientas necesarias ") {
                empleado2.puedeRealizarTarea(repararPc).shouldBeFalse()
            }
            it("empleadoCiclope puede realizar tarea de raparacion ") {
                empleado1.puedeRealizarTarea(repararMaquina).shouldBeTrue()
            }
        }

    }
    describe("Defensa") {
        describe("si el empleado puede defender el sector") {
            val sectorA = Sector(true, false, 20)
            val defensa = DefenderSector(sectorA)
            val obreroBiclope = Biclope(obrero, 10)
            val obreroCicople = Ciclople(obrero, 160)


            describe("El empleado no puede defender,ya que es LIMPIADOR") {
                Laboratorio.asignarRol(empleado2, Limpiador)
                it("no puede defender") {
                    empleado2.puedeRealizarTarea(defensa).shouldBeFalse()
                }
            }

            describe("empleados OBRERO") {
                it("pueden defender el sector") {
                    defensa.puedeSerRealizada(obreroBiclope).shouldBeFalse()
                    defensa.puedeSerRealizada(obreroCicople).shouldBeTrue()
                }
            }

            describe("empleados SOLDADO") {
                Laboratorio.asignarRol(empleado1, soldado)
                Laboratorio.asignarRol(empleado2, soldado)

                it("el empleado no pude realizar la tarea de defender el sector") {
                    obreroBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
                }
                it("el empleado si puede realizar la tarea de defenser el sector ") {
                    obreroCicople.puedeRealizarTarea(defensa).shouldBeTrue()
                }
            }
        }

    }
    describe("Limpieza") {

        it("Los limpiadores pueden limpiar siempre") {
            val mrMusculo = Biclope(Limpiador, 1)
            limpiarbanio.puedeSerRealizada(mrMusculo).shouldBeTrue()
        }
        it("No se puede limpiar porque es grande y tiene poca estamina") {
            val obreroBiclope = Biclope(obrero, 3)
            limpiarbanio.puedeSerRealizada(obreroBiclope).shouldBeFalse()
        }
        it("No se puede limpiar porque es chico y no tiene estamina") {
            val obreroDebil = Biclope(obrero, 0)
            //obreroDebil.rol.puedeRealizarTarea(limpiarCocina).shouldBeTrue()
            limpiarCocina.puedeSerRealizada(obreroDebil).shouldBeFalse()
        }
        it("Si se puede limpiar ya que tiene bastante estamina") {
            val obreroFuerte = Biclope(obrero, 4)
            limpiarbanio.puedeSerRealizada(obreroFuerte).shouldBeTrue()
        }
        it("Si se puede limpiar porque es chico") {
            val obreroMenosDebil = Biclope(obrero, 1)
            limpiarCocina.puedeSerRealizada(obreroMenosDebil).shouldBeTrue()
        }

    }
    describe("4.Realizar tareas") {
        val sectorA = Sector(true, false, 20)
        val defensa = DefenderSector(sectorA)
        val obreroBiclope = Biclope(obrero, 3)
        val obreroCicople = Ciclople(obrero, 160)


        describe("Defender") {

            it("Se puede defender") {
                obreroCicople.realizarTarea(defensa)
                sectorA.estaLimpio.shouldBeFalse()

            }
            it("No se puede defender y da error") {
                shouldThrowAny{ obreroBiclope.realizarTarea(defensa) }
            }

        }
        describe("Limpiar") {

            it("Se puede limpiar") {
                obreroCicople.realizarTarea(limpiarbanio)
                obreroCicople.estamina.shouldBe(150)

            }
            it("No se puede limpiar y da error") {

                //shouldThrowAny { obreroBiclope.realizarTarea(limpiarCocina) }

            }
        }
        describe("Arreglar") {

            it("Se puede arreglar") {
                empleado1.realizarTarea(repararMaquina)
                empleado1.estamina.shouldBe(25)

            }
            it("No se puede arreglar y da error") {
                shouldThrowAny { obreroBiclope.realizarTarea(repararMaquina) }

            }

        }

    }
    val obreroBiclope = Biclope(obrero,100)
    val obreroCicople = Ciclople(obrero,10)
    describe("5. nuevo rol capataz") {
        val capataz = Capataz()
        val empleadoCapataz = Ciclople(capataz, 100)
        capataz.subAlternos = mutableListOf(empleado1, empleado2,obreroBiclope,obreroCicople)
        capataz.subAlternos.size.shouldBe(4)
        capataz.experienciaCapataz().shouldBe(40)

    }

    describe("6. Laboratorio - esta en orden") {
        val sector1 = Sector(true, true, 0)
        val sector2 = Sector(true, true, 0)
        val sector3 = Sector(true, true, 0)


        Laboratorio.sectores = mutableListOf<Sector>(sector1, sector2, sector3)
        Laboratorio.empleados = mutableListOf<Minion>(obreroBiclope, obreroCicople)

        it("Todes contentes, sin amenazas y limpio") {
            Laboratorio.enOrden().shouldBeTrue()
        }
        it("Infeliz, sin amenazas y limpio") {
            obreroBiclope.estamina = 8
            Laboratorio.enOrden().shouldBeFalse()
        }
    }
    describe("7. Laboratorio - Jornada Laboral") {
        val sector1 = Sector(true, false, 4)
        val sector2 = Sector(true, false, 4)
        val sector3 = Sector(true, false, 0)

        val obreroInutil = Ciclople(obrero, 1)

        val defender1 = DefenderSector(sector1)
        val defender2 = DefenderSector(sector2)

        val limpiar3 = LimpiarSector(sector3)
        val repararReactor = ArreglarMaquina(mutableListOf("escabadientes"), 500000)

        it("Realizar todas las tareas") {
            Laboratorio.sectores = mutableListOf<Sector>(sector1, sector2, sector3)
            Laboratorio.empleados = mutableListOf<Minion>(obreroBiclope, obreroCicople)
            Laboratorio.tareasPendientes = mutableListOf<Tarea>(defender1, limpiar3)

            Laboratorio.jonadaLaboral()
            Laboratorio.tareasPendientes.shouldBeEmpty()

        }
        it("No se puede realizar debido a que no hay tareas") {
            Laboratorio.empleados = mutableListOf<Minion>(obreroBiclope, obreroCicople)

            shouldThrowAny {
                Laboratorio.jonadaLaboral()
            }
        }
        it("Nadie puede realizar las tareas") {
            Laboratorio.empleados = mutableListOf<Minion>(obreroInutil)
            Laboratorio.tareasPendientes = mutableListOf<Tarea>(defender1, defender2, repararPc, limpiar3)

            shouldThrowAny {
                Laboratorio.jonadaLaboral()
            }
        }
    }








})