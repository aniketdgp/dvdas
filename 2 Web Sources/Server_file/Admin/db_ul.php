<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $state = $_POST['state'];
 $dlno = $_POST['dlno'];
 $fullname = $_POST['fullname'];
 $sdwhof = $_POST['sdwhof'];
 $dob = $_POST['dob'];
 $age = $_POST['age'];
 $dateofissue = $_POST['dateofissue'];
 $validtill = $_POST['validtill'];
 $bloodgroup = $_POST['bloodgroup'];
 $permanentaddress = $_POST['permanentaddress'];
 $currentaddress = $_POST['currentaddress'];
 
 
 $verifyusernamesql = "select username from useraccount where username='$username'";
 $verifyusernamecheck = mysqli_fetch_array(mysqli_query($con,$verifyusernamesql));
 
 


 if(isset($verifyusernamecheck)){
 
           $Sql_Query = "update license_data set username='$username',state='$state',dlno='$dlno',fullname='$fullname',sdwhof='$sdwhof',dob='$dob',age='$age',dateofissue='$dateofissue',validtill='$validtill',bloodgroup='$bloodgroup',permanentaddress='$permanentaddress',currentaddress='$currentaddress' where username ='$username'";
           if(mysqli_query($con,$Sql_Query)){
           echo 'License Data Updated';
           }
           else{
           echo 'Something went wrong';
           }
     }

else{

echo $username." Doesn't Exists in Our DataBase ";

}
   
   
   




   
   
  }

   ?>