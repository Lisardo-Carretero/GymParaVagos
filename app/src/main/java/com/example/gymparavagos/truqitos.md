# Truquitos
* Dar formato al *PUTO CODIGO*
````aidl
CONTROL+ALT+L
````
* Crear Actividades
>  startActivity(Intent(this,UserPage::class.java)

y si quieres que no se pueda volver a la anterior

>finish()
* __Asociar Botones y otros atributos__

Es como crear una varaible de tipo Button y se asocian con findViewById que es como en esta pagina o 
vista encuentra por id a tal primo.
>val botonLog:Button = findViewById(R.id.botonLogin)
> 
> val campoUsuario: String = findViewById<EditText>(R.id.userLogin).text.toString().trim()

botonLog es la variable con atributo setOnclickListener eso es que cuando le des pues se ejecuta el metodo
login()
>botonLog.setOnClickListener { login() }



#  *Estructura de la bdd en firebase*
Usa json
* Estructura

 >Ejercicios
```
{
    nombre: String,
    reps: [] 
}
```
>Usuarios
```
{
    tabla : []{
        nombre: String,
        ejercicio: [{
            nombre: String,
            reps: []
        }]
    }
   
}
```