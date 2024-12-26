package com.example.Msd.entity;

public class Detailform {

	 private String actioncode;

	 public String getActioncode() {
	  return actioncode;
	 }

	 public void setActioncode(String actioncode) {
	  this.actioncode = actioncode;
	 }

	 public Detailform(String actioncode) {
	  super();
	  this.actioncode = actioncode;
	 }

	 public Detailform() {
	  super();
	 }

	 @Override
	 public String toString() {
	  return "Detailsform [actioncode=" + actioncode + "]";
	 }

	}