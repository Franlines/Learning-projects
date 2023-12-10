public class Anatomia_Grey {
    public static void main(String[] args) {
        Medico Meredith = new Residentes("Meredith Grey");
        Medico Shepherd = new Especialista("Derek Shepherd",especialidad.Neurologia);
        Medico Williams = new Residentes("Jesse Williams");
        Medico Karev = new Residentes("Alex Karev");
        Medico Sloan = new Especialista("Mark Sloan", especialidad.Plastica);
        Medico Stevens = new Residentes("Izzie Stevens");
        Medico Omalley = new Jefe_de_Internos("George O´Malley",curso.Cuarto);
        Medico Forbes = new Especialista("Addison Forbes",especialidad.Ginecologia);
        Medico Yang = new Residentes("Cristina Yang");
        Medico Hunt = new Jefe_de_Cirugia("Owen Hunt",especialidad.Trauma);
        Medico Lexie = new Residentes("Lexie Grey");
        Medico Torres = new Especialista("Callie Torres", especialidad.Ortopedia);
        Medico Bailey = new Especialista("Miranda Bailey", especialidad.Cirugia_General);
        Medico Robbins = new Especialista("Arizona Robbins", especialidad.Ginecologia);
        Medico Webber = new Jefe_de_Cirugia("Richar Webber", especialidad.Cardiologia);
        Medico Teddy= new Especialista("Teddy Altman", especialidad.Cardiologia);

        Omalley.Muere();
        Stevens.Muere();
        Sloan.Muere();

        Medico[] Hospital =new Medico[16];
        Hospital[0]=Meredith;
        Hospital[1]=Shepherd;
        Hospital[2]= Williams;
        Hospital[3]= Karev;
        Hospital[4] = Sloan;
        Hospital[5] = Stevens;
        Hospital[6] = Omalley;
        Hospital[7] = Forbes;
        Hospital[8] = Yang;
        Hospital[9] = Hunt;
        Hospital[10] = Lexie;
        Hospital[11] = Torres;
        Hospital[12] = Bailey;
        Hospital[13] = Robbins;
        Hospital[14] = Webber;
        Hospital[15] = Teddy;



        for (Medico medico:Hospital){
            if (!medico.Vive) {
                System.out.println(medico.getPropiedades() + ". Fallecido");
            }
            else
                System.out.println(medico.getPropiedades());
        }










    }
}

enum curso {Primero, Segundo, Tercero, Cuarto};
enum especialidad {Ginecologia, Cirugia_General, Ortopedia, Cardiologia, Neurologia, Plastica, Trauma}



abstract class Medico{
    public Medico(String nombre){
        this.nombre=nombre;
    }
    protected final String nombre;
    public String titulo;
    public String tareas;
    boolean Vive = true;

    public abstract String getTareas();
    public abstract String getPropiedades();
    public void Muere(){
        Vive = false;
    }
}



class Interno extends Medico{
    public curso curso;

    public Interno(String nombre, curso curso){
        super (nombre);
        titulo = "Interno";
        tareas = "Obedecer a su superior y aprender";
        this.curso = curso;
    }
    public String getTareas(){
        return "La tarea principal de un interno es" + tareas;
    }
    public String getPropiedades(){
        return nombre + " es un/a " + titulo + " de " + curso + " curso.";
    }
    public String getNombre(){
        return nombre;
    }
}



class Jefe_de_Internos extends Interno{
    public Jefe_de_Internos(String nombre,curso curso) {
        super(nombre, curso);
        tareas = "Coordinar el trabajo de los demas internos";
        titulo = "Jefe de Internos";
    }
    public String getTareas(){
        return "La tarea principal de un Jefe de internos es " + tareas;
    }
    public String getPropiedades(){
        return nombre + " es un/a " + titulo + " y esta en " + curso + " curso.";
    }
}


class Residentes extends Medico {
    public Residentes(String nombre) {
    super(nombre);
    tareas = "Apoyar a los especialistas en sus operaciones";
    titulo = "Residente";
    }
    public String getTareas(){
        return "La tarea principal de un residente es " + tareas;
    }
    public String getPropiedades(){
        return nombre + " es un/a " + titulo;
    }
    public String getNombre(){
        return nombre;
    }
}



class Especialista extends Residentes{
    public especialidad especialidad;

    public Especialista(String nombre, especialidad especialidad) {
        super(nombre);
        tareas = "Encargarse de las operaciones de su especialidad";
        titulo = "Especialista en " + especialidad;
    }
    public String getTareas(){
        return "La tarea de un especialista es " + tareas;
    }
    public String getPropiedades(){
        return nombre + " es un " + titulo;
    }
}



class Jefe_de_Cirugia extends Especialista{
    public Jefe_de_Cirugia(String nombre, especialidad especialidad){
        super(nombre,especialidad);
        tareas = "Dirigir a los especialistas de su hospital";
        titulo = "Jefe de Cirujia";
    }

    public String getTareas(){
        return "La tarea de un Jefe de Cirugia es " + tareas;
    }
    public String getPropiedades(){
        return nombre + " es el/la " + titulo;
    }
}
