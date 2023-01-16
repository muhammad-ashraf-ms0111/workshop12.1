package sg.app.workshop121.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.app.workshop121.exception.RandNumException;
import sg.app.workshop121.models.Generate;

@Controller
@RequestMapping(path="/rand")
public class GenRandNumController {
  

 @GetMapping(path="/show")
 public String showNum(Model model){
  Generate generateObj = new Generate();
  model.addAttribute("generateObj", generateObj);
  return "show";
 }

 @GetMapping(path="/generate")
 public String generateRandNumByGet(@RequestParam Integer numberVal, Model model){
     this.randomizeNum(model, numberVal.intValue());
     return "generate";
 }

//  @GetMapping(path="/generate/{numberVal}")
//  public String generateRandNumByGetPV(@PathVariable Integer numberVal, Model model){
//      this.randomizeNum(model, numberVal.intValue());
//      return "generate";
//  }

//  @PostMapping(path="/generate")
//  public String PostNum(Model model, @ModelAttribute Generate generate){
//   this.randomizeNum(model, generate.getNumberVal());
//   return "generate";
//  }
  
private void randomizeNum(Model model, int noOfGenNum){
  Integer MaxNumGen = 31;
  String[] nameImgNum = new String[MaxNumGen];

  if(noOfGenNum < 0 || noOfGenNum > 31){
    throw new RandNumException();}

  for (Integer i = 0; i < MaxNumGen; i++) {
    nameImgNum[i] = "number" + i + ".jpg";
  }

  Random randNum = new Random();
  Set<Integer> uniqueNumGen = new LinkedHashSet<Integer>();
  while(uniqueNumGen.size() < noOfGenNum) {
    Integer resultOfRandNum = randNum.nextInt(MaxNumGen);
    uniqueNumGen.add(resultOfRandNum);
  }

  List<String> selectedImg = new ArrayList<String>();
  Iterator<Integer> uniqueNums = uniqueNumGen.iterator();
  Integer currElem = null;
  while(uniqueNums.hasNext()){
    currElem = uniqueNums.next();
    selectedImg.add(nameImgNum[currElem]);
  }
  model.addAttribute("numberRandomNum", noOfGenNum);
  model.addAttribute("randNumResult", selectedImg.toArray());
}



}


