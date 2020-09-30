<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

 include '../DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $username =$_POST['username'];
 
 
 //for License Data Table
 $Sql_Query = "select * from license_data where username = '$username'"; 
 $select_query_result=mysqli_query($con,$Sql_Query)or die(mysqli_error($con));
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query)); // to check if the user has Driving License Data Uploaded via Admin
 $row= mysqli_fetch_array($select_query_result);
 //for License Data Table
  

 //for License Type Table
 $Sql_Query2 = "select * from license_type where username = '$username'"; 
 $select_query_result2=mysqli_query($con,$Sql_Query2)or die(mysqli_error($con));
 $check2 = mysqli_fetch_array(mysqli_query($con,$Sql_Query2)); // to check if the user has Driving License Data Uploaded via Admin
 $row2= mysqli_fetch_array($select_query_result2);
 //for License Type Table
  
  
 
 //ld = licensedata output
 //lt = licensetype output
      
 $ld = $row['username']."#".$row['state']."#".$row['dlno']."#".$row['fullname']."#".$row['sdwhof']."#".$row['dob']."#".$row['age']."#".$row['dateofissue']."#".$row['validtill']."#".$row['bloodgroup']."#".$row['permanentaddress']."#".$row['currentaddress']."#";                                                       
 $lt = $row2['mcwg']."#".$row2['mgv']."#".$row2['lmv']."#".$row2['hmv']."#".$row2['hgmv']."#".$row2['hpmv'];      
        
 $output = $ld.$lt;

 if(isset($check)){ 
 echo $output;
 }
 else{
 echo "NoData";
 }



}

?>