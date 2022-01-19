// We now import the connection object we exported in db.js.
const db = require("../controllers/db");

// More libraries…
const express = require("express");
const bodyParser = require("body-parser");
const mysql = require('mysql');
const router = express.Router();

router.use(bodyParser.json()); // Automatically parse all POSTs as JSON.
router.use(bodyParser.urlencoded({ extended: true })); // Automatically parse URL parameters



let mysqlcon = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "password",
    database : "FinalProject"} );
    
    mysqlcon.connect( function(err) 
    {
        if (err) throw err;
        console.log("Connected!");
    });
    

//     LOGIN VERIFICATION     
router.post("/userVerify", function (req, res) {
    let  username = req.body.username;
    let  password = req.body.password;

    let sql =`SELECT * FROM USERS WHERE username = '${username}' AND password =SHA2('${password}',512) ;`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// FIND REQUESTS

//     FETCH OBJECT     
router.post("/objectFetch", function (req, res) {
    let  category = req.body.category;

    let sql =`SELECT * FROM LOSTOBJS WHERE category = '${category}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});

//     FETCH QUESTIONS     
router.post("/answerFetch", function (req, res) {
    let  objID = req.body.objID;

    let sql =`SELECT * FROM OBJQs WHERE objID = '${objID}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});


//     MARK OBJECT AS FOUND    
router.post("/markFound", function (req, res) {
    let  objID = req.body.objID;
    let  name = req.body.name;
    let  description = req.body.description;
    let  image = req.body.image;
    let  SID = req.body.SID;
    let  category = req.body.category;

    let sql =`INSERT INTO FOUNDOBJS SELECT * FROM LOSTOBJS WHERE objID = '${objID}';`;

    let sql2 =`DELETE FROM LOSTOBJS WHERE objID = '${objID}';`;
    let sql3 =`DELETE FROM OBJQS WHERE objID = '${objID}';`;

    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            return res.json(result);
        }
    });

    mysqlcon.query(sql2, function (err, result) {
        console.log("Result: " + JSON.stringify(result));

    });    
    mysqlcon.query(sql3, function (err, result) {
        console.log("Result: " + JSON.stringify(result));

    });




});



//     GET FINDERS NUMBER TO SEND MESSAGE      
router.post("/getFinder", function (req, res) {
    let  SID = req.body.SID;

    let sql =`SELECT * FROM USERS WHERE SID = '${SID}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});


/////////////////////////////////////////////////////////////////////////////////////////////////////

// ADD REQUESTS

// ADD OBJECT TO LOSTOBJS DATABASE
router.post("/addObject", function (req, res) {
    let  name = req.body.name;
    let  description = req.body.description;
    let  image = req.body.image;
    let  SID = req.body.SID;
    let  category = req.body.category;

    let sql =`INSERT INTO LOSTOBJS (Name, description, image, SID, category) \
    VALUES ('${name}','${description}','${image}','${SID}','${category}');`;


    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            return res.json(result);
        }
    });
});

// ADD OBJECT ANSWERS TO OBJQS DATABASE

//     FETCH OBJECT     
router.post("/getLastObj", function (req, res) {
    let  category = req.body.category;

    let sql =`SELECT * FROM LOSTOBJS WHERE category = '${category}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});

router.post("/addAnswer", function (req, res) {
    let  answer1 = req.body.answer1;
    let  answer2 = req.body.answer2;
    let  answer3 = req.body.answer3;

    let sql =`INSERT INTO OBJQS (answer1, answer2, answer3)
             VALUES ('${answer1}','${answer2}','${answer3}');`;


    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            return res.json(result);
        }
    });
});


/////////////////////////////////////////////////////////////////////////////////////////////////////

// REPORT REQUESTS

//     FETCH OBJECT     
router.post("/foundFetch", function (req, res) {
    let  category = req.body.category;

    let sql =`SELECT * FROM FOUNDOBJS WHERE category = '${category}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});


//     GET FINDERS NUMBER TO SEND MESSAGE      
router.post("/findFinder", function (req, res) {
    let  SID = req.body.SID;

    let sql =`SELECT * FROM USERS WHERE SID = '${SID}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});

//     ADD NOTIFICATION     
router.post("/addNotify", function (req, res) {
    let  category = req.body.category;
    let  phonenum = req.body.phonenum;
    let sql =`INSERT IGNORE INTO NOTIFICATIONS (phonenum, category) VALUES ('${phonenum}','${category}');`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });

});

//     FETCH NOTIFICATION     
router.post("/notify", function (req, res) {
    let  phonenum = req.body.phonenum;

    let sql =`SELECT * FROM NOTIFICATIONS WHERE phonenum = '${phonenum}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});



//     DELETE NOTIFICATION     
router.post("/deleteNotify", function (req, res) {
    let  category = req.body.category;
    let  phonenum = req.body.phonenum;

    let sql =`DELETE FROM NOTIFICATIONS WHERE category = '${category}' AND phonenum = '${phonenum}' ;`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});


//     FETCH Category     
router.post("/notifyCategory", function (req, res) {
    let  category = req.body.category;

    let sql =`SELECT * FROM NOTIFICATIONS WHERE category = '${category}';`;
    mysqlcon.query(sql, function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            // let returnedObject = {};
            // Your code here
            return res.json(result);
        }
    });
});


//////////////////////////////////////////////////////////////////////////////

// Hello World
router.get("/health", function (req, res) {
    return res.send("ok"); // For plain text, use res.send
});


// Export the created router
module.exports = router;