<?php

include "connection.php";

$method = $_SERVER['REQUEST_METHOD'];
echo "METHOD: ".$method;
echo "PATH INFO".$_SERVER['PATH_INFO']."\n  ";
//$requestParameters = explode("/", substr(@$_SERVER['PATH_INFO'], 1));
$params =  urldecode($_GET["weather"]);
echo  ";;;Params".$params;


$get_parameters = array();
if (isset($_SERVER['QUERY_STRING'])) {
    $pairs = explode('&', $_SERVER['QUERY_STRING']);
    foreach($pairs as $pair) {
        $part = explode('=', $pair);
        $get_parameters[$part[0]] = sizeof($part)>1 ? urldecode($part[1]) : "";
    }
}
$tableName = $get_parameters["table"];
$valueDateParameter = $get_parameters["date"];
$valueCityToAnalize = $get_parameters["cityanalize"];
$valueDateToAnalize = $get_parameters["dateanalize"];
echo " DATE param ".$valueDateParameter;
echo " CITYANALIZE param ".$valueCityToAnalize;
echo " DATAANALIZE param ".$valueDateToAnalize;

echo '<pre>'; print_r($get_parameters); echo '</pre>';
$result = array();
switch ($method) {
    case 'POST':
        echo '<pre>';
        echo "post\n";
        $city = $_POST["city"];
        $currentDate = $_POST["currentDate"];
        $windspeed = $_POST["windspeed"];
        $humidity = $_POST["humidity"];
        $current_code = $_POST["current_code"];
        $currentTemperature = $_POST["currentTemperature"];
        $pred_temperatureLowyTomorrow = $_POST["pred_temperatureLowyTomorrow"];
        $pred_temperatureHighTomorrow = $_POST["pred_temperatureHighTomorrow"];
        $pred_codeTomorrow = $_POST["pred_codeTomorrow"];
        $pred_temperatureLowyAFTERTomorrow = $_POST["pred_temperatureLowyAFTERTomorrow"];
        $pred_temperatureHighAFTERTomorrow = $_POST["pred_temperatureHighAFTERTomorrow"];
        $pred_codeAFTERTomorrow = $_POST["pred_codeAFTERTomorrow"];
        $pred_data_tomorrow = $_POST["pred_data_tomorrow"];
        $pred_data_aftertomorrow = $_POST["pred_data_aftertomorrow"];

        echo "\nCurrent date ".$currentDate;
        echo "\nTommorow date ".$pred_data_tomorrow;
        echo "\npred_temperatureLowyTomorrow ".$pred_temperatureLowyTomorrow;
        echo "\npred_temperatureHighTomorrow".$pred_temperatureHighTomorrow;
        echo "\ncity".$city;
        echo "\nwindspeed".$windspeed;




        $inserting_query2 = "INSERT INTO cities (city_id, city_name, country, region) VALUES ('','$city','Metaland','Zemsnariad')";
        $inserting_query1 = "INSERT INTO conditon (condition_id, condition_city_id, condition_date_id, condition_temperature, condition_wind, condition_humidity, condition_code) VALUES ('',(SELECT city_id FROM cities WHERE city_name='$city'),(SELECT date_id FROM dates WHERE date='$currentDate'),'$currentTemperature','$windspeed','$humidity','$current_code')";

        $inserting_query_dates = "INSERT INTO dates (date_id, date) VALUES ('','$currentDate')";
        $inserting_query_dates2 = "INSERT INTO dates (date_id, date) VALUES ('','$pred_data_tomorrow')";


        $inserting_query3 = "INSERT INTO prediction (prediction_id, prediction_city_id, prediction_date_id, prediction_temperature_low, prediction_temperature_high, prediction_wind, prediction_humidity, prediction_code)
          VALUES ('',(SELECT city_id FROM cities WHERE city_name='$city'),(SELECT date_id FROM dates WHERE date='$pred_data_tomorrow'),'$pred_temperatureLowyTomorrow',
            '$pred_temperatureHighTomorrow','','','$pred_codeTomorrow')";



        if($connection->query($inserting_query2)===true){
            echo "Insertion  cities has succed\n";

        }else{
            echo "insertion cities unfortunately failed \n";
        }


        if($connection->query($inserting_query_dates)===true){
            echo "Insertion  dates has succed\n";

        }else{
            echo "insertion dates unfortunately failed \n";
        }
        if($connection->query($inserting_query_dates2)===true){
            echo "Insertion  predicted dates has succed\n";

        }else{
            echo "insertion predicted days unfortunately failed \n";
        }
        if($connection->query($inserting_query1)===true){
            echo "Insertion  condition has succed\n";

        }else{
            echo "insertion condition unfortunately failed \n";
        }

        if($connection->query($inserting_query3)===true){
            echo "Insertion  prediction has succed\n";

        }else{
            echo "insertion prediction unfortunately failed \n";
        }
        echo "post is OK\n";
        break;//POST
    case 'GET'://GET
        echo "Im in get in php\n";
        switch ($params){
            case 'weather':
                //$table = mysqli_real_escape_string($connection,$params);
                $query_select_allweather = "SELECT * FROM weatherdata.$tableName;";
//                $select = mysql_query($sql);
//                $connection->query($select);
                $sth = mysqli_query($connection,$query_select_allweather);
                $rows = array();
                while($r = mysqli_fetch_assoc($sth)) {
                    $rows[] = $r;
                }
                print json_encode($rows);
                                    
                break;
            case 'average':
                $rowAvg = array();
                echo "value in switch: ".$valueDateParameter;
                $average = mysqli_query($connection,
                    "CALL avg_temp('$valueDateParameter')") or die("Query fail: " . mysqli_error($connection));

                while ($obj = mysqli_fetch_assoc($average)) {
                    $rowAvg[] = $obj;
                }
                print json_encode($rowAvg);
                //print json_encode($rows);
                break;
            case 'analizer':
                $rowAnalized  = array();

                $analized = mysqli_query($connection,"CALL account_count('$valueCityToAnalize', '$valueDateToAnalize')") or die ("Query fail: ".mysqli_error($connection));
                while ($obj = mysqli_fetch_assoc($analized)) {
                    $rowAnalized[] = $obj;
                }
                print json_encode($rowAnalized);
                break;

            default :
                echo "sorry, wrong url";
                break;

        }
        echo "get";
        break;
    case 'DELETE':
        echo "delete";
        break;
    default:
        echo  "error";
        break;
}
