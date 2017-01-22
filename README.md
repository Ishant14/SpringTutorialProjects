if we to run on any type whether public or private ,irrespective of return type,
       any type get method like getName, get getTriangle etc then user = @Before("execution(* get*())")  */

    //if we want to run advice on any get method whether it takes argument or not then we will write as =  get*(..)

    /* if we want to run the get method but of particular package then we will write as =
       @Before("execution(* packageName.className.get*())")   */
