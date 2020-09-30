<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $licensetype = $_POST['licensetype'];

 
 $CheckSQL = "SELECT * FROM license_type WHERE username='$username'"; //checks if user exists in useraccount table
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){
 
                 $check_data = "select * from license_type where username = '$username'";
                 $select_query_result_check_data=mysqli_query($con,$check_data)or die(mysqli_error($con));
                 $row= mysqli_fetch_array($select_query_result_check_data);
                   
                   
                 if($licensetype=='MCWG' || $licensetype=='mcwg'){
                 if($row['mcwg']=='NA'){
                 $Sql_Query = "update license_type set mcwg='YES' where username = '$username' ";
                 $flag='y';
                 }
                 else{
                 echo "License Type Already Exists for the User";
                 }
                 }
                 else if($licensetype=='MGV' || $licensetype=='mgv'){
                 if($row['mgv']=='NA'){
                 $Sql_Query = "update license_type set mgv='YES' where username = '$username' ";
                 $flag='y';
                 }
                 else{
                 echo "License Type Already Exists for the User";
                 }
                 }
                 else if($licensetype=='LMV' || $licensetype=='lmv'){
                 if($row['lmv']=='NA'){
                 $Sql_Query = "update license_type set lmv='YES' where username = '$username' ";
                 $flag='y';
                 }
                 else{
                 echo "License Type Already Exists for the User";
                 }
                 }
                 else if($licensetype=='HMV' || $licensetype=='hmv'){
                 if($row['hmv']=='NA'){
                 $Sql_Query = "update license_type set hmv='YES' where username = '$username' ";
                 $flag='y';
                 }
                 else{
                 echo "License Type Already Exists for the User";
                 }
                 }
                 else if($licensetype=='HGMV' || $licensetype=='hgmv'){
                 if($row['hgmv']=='NA'){
                 $Sql_Query = "update license_type set hgmv='YES' where username = '$username' ";
                 $flag='y';
                 }
                 else{
                 echo "License Type Already Exists for the User";
                 }
                 }
                 else if($licensetype=='HPMV' || $licensetype=='hpmv'){
                 if($row['hpmv']=='NA'){
                 $Sql_Query = "update license_type set hpmv='YES' where username = '$username' ";
                 $flag='y';
                 }
                 else{
                 echo "License Type Already Exists for the User";
                 }
                 }
                 else{
                 echo "Wrong Entry Of License Category ";
                 }
          
        
           if($flag=='y'){
           mysqli_query($con,$Sql_Query);
           echo 'Licence Type Updated';
           }
         
   }
   else{
   echo $username." Dosen't Exists in Our DataBase";
   }
   
   
 }

   ?>