require 'watir-webdriver'
require 'headless'

usage_msg = "Usage: update_jenkins_ami_headless.rb username password ami_value"

if ARGV.length != 3 then
	puts "failed - #{usage_msg}"
	exit -1
end

username = ARGV[0]
password = ARGV[1]
ami_value = ARGV[2]

headless = Headless.new #:destroy_at_exit => false
headless.start


@browser = Watir::Browser.new

@browser.goto("http://ci.rice.kuali.org")

@browser.text_field(:id => "username").wait_until_present
@browser.text_field(:id => "username").set username

@browser.text_field(:id => "password").set password

@browser.button(:name => "submit").click


@browser.link(:text => "Manage Jenkins").wait_until_present
@browser.link(:text => "Manage Jenkins").click


@browser.td(:id => "main-panel").h1(:text => "Manage Jenkins").wait_until_present

@browser.table(:id => "management-links").link(:text => "Configure System").click

@browser.text_field(:name => "_.ami").wait_until_present

puts @browser.text_field(:name => "_.ami").value
puts "updating ami value to #{ami_value}"

@browser.text_field(:name => "_.ami").set ami_value
@browser.button(:name => "Save").click

@browser.link(:text => "log out").click
