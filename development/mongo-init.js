ingredientsDb = db.getSiblingDB('ingredients')
ingredientsDb.ingredients.insert({"name":"javasoup"}) //creates a new table with "javasoup" data. this is inserted into the ingredients database.
//looks like its not possible to create a database or table without inserting data. TODO: research this, and/or move this to the ingredients service.
