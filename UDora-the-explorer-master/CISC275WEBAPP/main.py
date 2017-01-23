#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Copyright 2007 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# diingibuted under the License is distringibuted on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


from header import *
import model
JINJA_ENVIRONMENT = jinja2.Environment(
    loader=jinja2.FileSystemLoader(os.path.dirname(__file__)),
    extensions=['jinja2.ext.autoescape'])

class Landing(webapp2.RequestHandler):
    def get(self):
        template_values = {
        }
        template = JINJA_ENVIRONMENT.get_template('index.html')
        self.response.write(template.render(template_values))

class post(webapp2.RequestHandler):
    def post(self):
        user = model.getUser(self.request.cookies.get('user',''),self.request.cookies.get('pass',''))
        if not(user):
            self.redirect('/')
            return
        if user.admin:
            self.redirect('/gensettings.html')
            return
        if not(user.studentgroupname):
            self.redirect('/calendar.html')
            return
        request = model.StudentGroupRequests.get_by_id(int(self.request.get('id')))
        studentgroup = model.StudentGroups(
            student = request.student,
            groupname = request.groupname)
        studentgroup.put()
        request.key.delete()
        time.sleep(.5)
        self.redirect('/mystudents.html')


class LogIn(webapp2.RequestHandler):
    
    def post(self):
            confirm ="true"
            username = self.request.get('username')
            password = self.request.get('password')
            user = model.getUserNoPassword(username)
            if not(user):
                self.redirect('/')
                confirm ="false"
                return
            password = hashlib.sha224(self.request.get('password')).hexdigest()
            if not(password==user.password):
                self.redirect('/')
                confirm ="false"
                return
            template_values = {
                'confirmed':confirm
            }
            self.response.write("True")
            self.response.headers.add_header( "Set-Cookie","user=%s; path=/" % username.__str__())
            self.response.headers.add_header( "Set-Cookie","pass=%s; path=/" % self.request.get('password').__str__())
      
class CreateUser(webapp2.RequestHandler):
    def post(self):
        user = model.Users(username = self.request.get('username'),
                     password = hashlib.sha224(self.request.get('password')).hexdigest(),
                     email = self.request.get('email'))
        user.put()

       # username = self.request.get('username')
       # password = hashlib.sha224(self.request.get('password')).hexdigest()
        
       # self.response.headers.add_header( "Set-Cookie","user=%s; path=/" % username.__str__())
       # self.response.headers.add_header( "Set-Cookie","pass=%s; path=/" % self.request.get('password').__str__())
        
        self.response.write('User Created!')
        avatarfile = ''

        self.redirect('/')
class add(webapp2.RequestHandler):
    def post(self):
        
        if (model.getUserNoPassword(self.request.get('username'))):
            myuser = model.Users(username = self.request.get('username'),
            online_overallpercent = float(self.request.get('online_overallpercent')),
            online_amountofplays = int(self.request.get('online_amountofplays')),
            online_numofsuccess = int(self.request.get('online_numofsuccess')),
            resources_overallpercent =int(self.request.get('resources_overallpercent')),
            resources_amountofplays = int(self.request.get('resources_amountofplays')),
            resources_numofsuccess = float(self.request.get('resources_numofsuccess')),
            calendar_overallpercent =int(self.request.get('calendar_overallpercent')),
            calendar_amountofplays = int(self.request.get('calendar_amountofplays')),
            calendar_numofsuccess = int(self.request.get('calendar_numofsuccess')),
            advisement_overallpercent =float(self.request.get('advisement_overallpercent')),
            advisement_amountofplays = int(self.request.get('advisement_amountofplays')),
            advisement_numofsuccess = int(self.request.get('advisement_numofsuccess')))
            myuser.put()
            Users.key.delete()
        myuser1 = model.Users(
            username = self.request.get('username'),
            online_overallpercent =float(self.request.get('online_overallpercent')),
            online_amountofplays = int(self.request.get('online_amountofplays')),
            online_numofsuccess = int(self.request.get('online_numofsuccess')),
            resources_overallpercent =float(self.request.get('resources_overallpercent')),
            resources_amountofplays = int(self.request.get('resources_amountofplays')),
            resources_numofsuccess = int(self.request.get('resources_numofsuccess')),
            calendar_overallpercent =float(self.request.get('calendar_overallpercent')),
            calendar_amountofplays = int(self.request.get('calendar_amountofplays')),
            calendar_numofsuccess = int(self.request.get('calendar_numofsuccess')),
            advisement_overallpercent =float(self.request.get('advisement_overallpercent')),
            advisement_amountofplays = int(self.request.get('advisement_amountofplays')),
            advsuccess = int(self.request.get('advsuccess')))
        myuser1.put()
        
         
        self.redirect('/')
class Login(webapp2.RequestHandler):

    def post(self):
        redirecte ='/calendar.html'
        username = self.request.get('username')
        user = model.getUserNoPassword(username)
        if not(user):
          #  self.response.write("false")
            return
        password = hashlib.sha224(self.request.get('password')).hexdigest()
        if not(password==user.password):
          #  self.response.write("false")
            self.redirect('/')
            return
        self.response.write("True")
        #self.response.headers.add_header( "Set-Cookie","user=%s; path=/" % username.__str__())
        #self.response.headers.add_header( "Set-Cookie","pass=%s; path=/" % self.request.get('password').__str__())
        #self.redirect(redirecte)
        
app = webapp2.WSGIApplication([
    ('/', Landing),
    ('/createuser',CreateUser),
    ('/login',Login),
    ('/addData',add)
], debug=True)
