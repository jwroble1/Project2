<?php

  /**************************
   * File: GetData.php
   * Author: Joseph Wrobleski
   * Email: jwroble1@umbc.edu
   * Project 2
   * File Description:
   * This file takes the values from table 'Courses' and
   * stores them as a list.
   ***************************/

  //common methods

include('/afs/umbc.edu/users/j/w/jwroble1/pub/www/CMSC331/CommonMethods.php');

include('JSON.php');

/*
error_reporting(E_ALL | E_STRICT | E_NOTICE);
ini_set('display_errors','1');
*/
$debug = false;

$COMMON = new COMMON($debug);

$data = array();

$index = 0;

// obtains all information from couses
$sql = "select * from Courses";

$result = $COMMON->executeQuery($sql, $_SERVER["studentdb.gl.umbc.edu"]);

while($row = mysql_fetch_assoc($result))
  {

    $data[$index] = $row;
    $index++;

  }


$json = new Services_JSON();

echo $json->encode($data);

exit;

}

?>
